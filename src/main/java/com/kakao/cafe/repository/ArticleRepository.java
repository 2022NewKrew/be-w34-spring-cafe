package com.kakao.cafe.repository;

import com.kakao.cafe.model.Article;

import java.util.List;

public interface ArticleRepository {
    void save(String title, String content);
    Article findArticleById(int id);
    List<Article> findAllArticles();
}
