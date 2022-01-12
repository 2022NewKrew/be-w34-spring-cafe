package com.kakao.cafe.module.repository;

import com.kakao.cafe.module.model.domain.Article;

import java.util.List;

public interface ArticleRepository {

    void addArticle(Article article);

    List<Article> findAllArticles();

    Article findArticleById(Long id);
}
