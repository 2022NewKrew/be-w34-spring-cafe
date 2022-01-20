package com.kakao.cafe.article.application.service;

import com.kakao.cafe.article.application.port.in.CommentDeletionUseCase;
import com.kakao.cafe.article.application.port.out.DeleteCommentPort;
import com.kakao.cafe.common.exception.DeletionException;

import java.util.NoSuchElementException;

public class CommentDeletionService implements CommentDeletionUseCase {
    private final DeleteCommentPort deleteCommentPort;

    public CommentDeletionService(DeleteCommentPort deleteCommentPort) {
        this.deleteCommentPort = deleteCommentPort;
    }

    @Override
    public void deleteComment(Long articleId, Long commentId, Long userId) {
        Boolean ableToDelete = deleteCommentPort.isAbleToDelete(commentId, userId).orElseThrow(NoSuchElementException::new);
        if (!ableToDelete) throw new DeletionException();
        deleteCommentPort.deleteComment(articleId, commentId);
    }
}
