package com.kakao.cafe.article.domain;

import java.util.ArrayList;
import java.util.Optional;

public interface ArticleRepository {

    Long persist(Article article);

    Optional<Article> find(Long id);

    ArrayList<Article> findAll();

    void increaseHit(Long id);

    void
    updateArticle(Article article);
}
