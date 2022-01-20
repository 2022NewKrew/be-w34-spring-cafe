package com.kakao.cafe.article.domain;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Long persist(Article article);

    Optional<Article> find(Long id);

    List<Article> findAll();

    void increaseHit(Long id);

    void
    updateArticle(Article article);
}
