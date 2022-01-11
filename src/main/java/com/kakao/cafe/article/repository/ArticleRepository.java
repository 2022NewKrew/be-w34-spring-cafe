package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.entity.Article;

import java.util.List;

public interface ArticleRepository {
    public Article save(Article user);

    public Article findById(Long id);

    public List<Article> findAll();
}
