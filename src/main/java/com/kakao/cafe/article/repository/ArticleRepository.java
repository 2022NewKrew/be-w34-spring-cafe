package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Article save(Article article);

    Optional<Article> findOne(Long id);

    List<Article> findAll();

    void update(Long id, Article article);

    void delete(Long id);

}
