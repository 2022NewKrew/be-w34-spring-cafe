package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    void saveArticle(Article article);

    Optional<Article> findArticleById(Long qnAId);

    List<Article> findAll();
}
