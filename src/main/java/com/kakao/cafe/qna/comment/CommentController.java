package com.kakao.cafe.qna.comment;

import com.kakao.cafe.qna.article.ArticleService;
import com.kakao.cafe.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by melodist
 * Date: 2022-01-19 019
 * Time: 오후 6:07
 */
@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class CommentController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final HttpSession session;

    @PostMapping("/{articleIdx}")
    public String makeComment(String contents, @PathVariable Integer articleIdx) {
        User user = getSessionedUser();
        if (user == null) {
            return "/user/login";
        }

        commentService.insertComment(user.getId(), articleIdx, user.getUserId(), contents);
        Integer commentsCount = articleService.findArticleById(articleIdx).getCommentsCount();
        articleService.updateCommentsCount(articleIdx, commentsCount + 1);

        return "redirect:/articles/{articleIdx}";
    }

    @PutMapping("/{articleIdx}/comments/{commentIdx}")
    public String editComment(@PathVariable Integer articleIdx, @PathVariable Integer commentIdx) {
        return null;
    }

    @DeleteMapping("{articleIdx}/comments/{commentIdx}")
    public String deleteComment(@PathVariable Integer articleIdx, @PathVariable Integer commentIdx) {
        User user = getSessionedUser();
        if (user == null) {
            return "/user/login";
        }

        Comment comment = commentService.findCommentById(articleIdx, commentIdx);

        // 댓글 작성자 ID와 삭제 요청자 ID가 일치해야 함
        if (!user.getUserId().equals(comment.getWriter())) {
            return "/qna/show_edit_failed";
        }

        commentService.deleteComment(comment);

        Integer commentsCount = articleService.findArticleById(articleIdx).getCommentsCount();
        articleService.updateCommentsCount(articleIdx, commentsCount - 1);

        return "redirect:/articles/{articleIdx}";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentException() {
        return "/qna/show_edit_failed";
    }

    private User getSessionedUser() {
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return null;
        }

        return (User) value;
    }
}
