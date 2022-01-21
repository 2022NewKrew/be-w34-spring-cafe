package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    Long getNumberOfArticles();

    List<Article> findAll();

    Optional<Article> findOneById(Long id);

    void updateOne(Article article);

    void deleteById(Long id);
}
