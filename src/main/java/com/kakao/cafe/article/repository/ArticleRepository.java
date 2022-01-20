package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.persistence.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    List<Article> findAll();
    Optional<Article> findById(Long id);

    void update(Article article);
    void delete(Article article);
}
