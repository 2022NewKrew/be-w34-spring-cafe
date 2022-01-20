package com.kakao.cafe.Repository;

import com.kakao.cafe.Domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    void saveComment(Comment comment);

    List<Comment> findCommentsOf(Long articleId);

    Optional<Comment> findByCommentId(Long id);

    void editComment(Long commentId, Comment comment);

    void deleteComment(Long commentId);
}
