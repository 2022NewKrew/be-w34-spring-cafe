package com.kakao.cafe.domain.comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    void save(Comment comment);
    Optional<Comment> findById(long id);
    List<Comment> findByPostId(long postId);
    void deleteById(long id);
}
