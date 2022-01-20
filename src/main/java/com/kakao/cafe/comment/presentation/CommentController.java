package com.kakao.cafe.comment.presentation;

import com.kakao.cafe.comment.application.CommentService;
import com.kakao.cafe.comment.application.dto.CommentSaveRequest;
import com.kakao.cafe.user.domain.SessionedUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static com.kakao.cafe.article.presentation.ArticleController.ARTICLE_URI;
import static com.kakao.cafe.comment.presentation.CommentController.COMMENT_URI;

@Controller
@Slf4j
@RequestMapping(COMMENT_URI)
public class CommentController {

    public static final String COMMENT_URI = ARTICLE_URI + "/{articleId}/comments";

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public String save(
            @PathVariable int articleId,
            CommentSaveRequest commentSaveRequest,
            HttpSession session
    ) {
        log.info(this.getClass() + ": 댓글 작성");
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return "redirect:/auth/login";
        }

        SessionedUser user = (SessionedUser) value;
        commentService.save(articleId, user, commentSaveRequest);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/{commentId}")
    public String deleteById(
            @PathVariable int articleId,
            @PathVariable String commentId,
            HttpSession session
    ) {
        log.info(this.getClass() + ": 댓글 삭제");
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return "redirect:/auth/login";
        }

        SessionedUser user = (SessionedUser) value;
        commentService.deleteById(commentId, user);
        return "redirect:/articles/" + articleId;
    }
}
