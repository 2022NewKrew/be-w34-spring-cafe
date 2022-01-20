package com.kakao.cafe.comment.domain;

import java.util.List;

public interface CommentRepository {
    List<Comment> findAllByArticleId(int articleId);

    int save(Comment comment);

    Comment findByIdOrNull(String commentId);

    void delete(Comment comment);
}
