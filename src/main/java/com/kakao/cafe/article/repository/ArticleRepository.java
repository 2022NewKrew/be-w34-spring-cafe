package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.entity.Article;

import java.util.List;

public interface ArticleRepository {
    void save(Article toEntity);
    List<Article> findAll();
    Article findById(Long id);
}
