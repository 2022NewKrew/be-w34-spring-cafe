package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> saveComment(long articleId, Comment comment);

    List<Comment> findCommentsByArticleId(long articleId);

    Optional<Comment> findCommentByArticleIdAndCommentId(long articleId, long commentId);

    List<Comment> findCommentsByWriterId(String writerId);

    boolean modifyComment(long articleId, Comment comment);

    boolean deleteCommentByArticleId(long articleId);

    boolean deleteComment(long articleId, long commentId);
}
