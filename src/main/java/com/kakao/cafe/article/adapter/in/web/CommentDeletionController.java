package com.kakao.cafe.article.adapter.in.web;

import com.kakao.cafe.article.application.port.in.CommentDeletionUseCase;
import com.kakao.cafe.common.exception.DeletionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.NoSuchElementException;

@Controller
public class CommentDeletionController {
    private final CommentDeletionUseCase commentDeletionUseCase;

    public CommentDeletionController(CommentDeletionUseCase commentDeletionUseCase) {
        this.commentDeletionUseCase = commentDeletionUseCase;
    }

    @DeleteMapping("/articles/{articleId}/comments/{commentId}")
    public String deleteComment(@PathVariable("articleId") Long articleId,
                                @PathVariable("commentId") Long commentId,
                                @SessionAttribute("userKey") Long userId) throws DeletionException, NoSuchElementException {
        commentDeletionUseCase.deleteComment(articleId, commentId, userId);
        return "redirect:/articles/" + articleId;
    }
}
