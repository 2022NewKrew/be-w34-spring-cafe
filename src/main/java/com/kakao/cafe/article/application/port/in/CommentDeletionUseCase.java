package com.kakao.cafe.article.application.port.in;

public interface CommentDeletionUseCase {
    void deleteComment(Long articleId, Long commentId, Long userId);
}
