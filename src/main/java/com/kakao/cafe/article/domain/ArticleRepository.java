package com.kakao.cafe.article.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository {
    Article save(Article article);

    List<Article> findAll();

    Article findByIdOrNull(String articleId);
}
