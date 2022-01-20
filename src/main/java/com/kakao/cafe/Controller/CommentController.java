package com.kakao.cafe.Controller;

import com.kakao.cafe.Domain.Comment;
import com.kakao.cafe.Domain.User;
import com.kakao.cafe.Service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/articles/{articleId}/comments")
    public String postComments(@PathVariable("articleId") int articleId,
                               Comment comment, HttpSession session, Model model) {
        logger.info("POST /comment/{} : Create a comment on article({})", articleId, articleId);

        try {
            Object sessionedUser = session.getAttribute("sessionedUser");
            commentService.postComment(comment, sessionedUser);
            logger.info("Comment was posted by {}", comment.getAuthor());

            return "redirect:/articles/{articleId}";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "error/page";
        }
    }

    @GetMapping("/edit-comment/{articleId}/comments/{commentId}")
    public String getEditArticle(@PathVariable("articleId") Long articleId,
                                 @PathVariable("commentId") Long commentId,
                                 HttpSession session, Model model) throws IllegalAccessException {
        logger.info("GET /edit-comment : Load comment edit form");
        try {
            User sessionedUser = (User) session.getAttribute("sessionedUser");
            commentService.checkAuthorMatch(commentId, sessionedUser);
            Comment findComment = commentService.findOne(commentId).get();

            model.addAttribute("comment", findComment);
            return "post/edit_comment";
        } catch (IllegalAccessException e) {
            model.addAttribute("error", e.getMessage());
            return "error/page";
        }
    }

    @PutMapping("/articles/{articleId}/comments/{commentId}")
    public String putEditArticle(@PathVariable("articleId") Long articleId,
                                 @PathVariable("commentId") Long commentId,
                                 Comment comment, HttpSession session, Model model) {
        logger.info("PUT /articles/{}/comments/{} : Edit comment({})", articleId, commentId, commentId);
        try {
            commentService.edit(commentId, comment);
            logger.info("Comment({}) of article({}) was edited", commentId, articleId);

            return "redirect:/articles/{articleId}";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error/page";
        }
    }
    @DeleteMapping("/articles/{articleId}/comments/{commentId}")
    public String deleteArticle(@PathVariable("articleId") Long articleId,
                                @PathVariable("commentId") Long commentId,
                                HttpSession session, Model model) {
        logger.info("DELETE /articles/{}/comments/{} : Edit comment({})", articleId, commentId, commentId);
        try {
            User sessionedUser = (User) session.getAttribute("sessionedUser");
            commentService.checkAuthorMatch(commentId, sessionedUser);
            commentService.delete(commentId);
            logger.info("Comment({}) of article({}) was deleted", commentId, articleId);

            return "redirect:/articles/{articleId}";
        } catch (IllegalAccessException e) {
            model.addAttribute("error", e.getMessage());
            return "error/page";
        }
    }
}
