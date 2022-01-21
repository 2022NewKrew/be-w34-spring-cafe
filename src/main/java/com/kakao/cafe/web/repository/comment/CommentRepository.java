package com.kakao.cafe.web.repository.comment;


import com.kakao.cafe.web.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    void save(Comment comment);
    void delete(Long id);
    Optional<Comment> findById(Long id);
    List<Comment> findAllByArticleId(Long articleId);
}
