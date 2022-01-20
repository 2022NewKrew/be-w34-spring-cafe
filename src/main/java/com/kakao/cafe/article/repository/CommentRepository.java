package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.model.Comment;

import java.util.List;

public interface CommentRepository {

    void save(Comment comment);

    List<Comment> findAllByArticleId(Long articleId);
}
