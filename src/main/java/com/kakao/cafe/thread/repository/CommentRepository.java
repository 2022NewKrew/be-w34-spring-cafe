package com.kakao.cafe.thread.repository;

import com.kakao.cafe.thread.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Long add(Comment comment);

    List<Comment> getCommentsForPost(Long postId);

    Optional<Comment> get(Long Id);

    void update(Comment comment);
}
