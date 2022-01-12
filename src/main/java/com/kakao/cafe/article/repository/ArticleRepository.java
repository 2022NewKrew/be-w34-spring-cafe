package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.model.Article;

import java.util.List;

public interface ArticleRepository {

    void save(Article article);

    Long getNumberOfArticles();

    List<Article> findAll();

    Article findOneById(Long id);
}
