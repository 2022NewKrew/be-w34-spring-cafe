package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    void save(Comment comment);

    List<Comment> findAllByArticleId(Long articleId);

    void deleteCommentById(Long id);

    void deleteByArticleId(Long articleId);

    Optional<Comment> findOneById(Long id);
}
