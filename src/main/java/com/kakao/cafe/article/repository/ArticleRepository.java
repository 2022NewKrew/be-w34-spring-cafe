package com.kakao.cafe.article.repository;

import java.util.List;
import java.util.Optional;

import com.kakao.cafe.article.entity.Article;

public interface ArticleRepository {
    void save(Article article);

    List<Article> findAll();

    Optional<Article> findById(int id);

    void update(int id, Article article);

    void delete(int id);
}
