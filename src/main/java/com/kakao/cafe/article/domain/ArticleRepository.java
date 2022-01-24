package com.kakao.cafe.article.domain;

import java.util.List;

public interface ArticleRepository {
    int save(Article article);

    List<Article> findAll();

    Article findByIdOrNull(int articleId);

    void update(Article article);

    void delete(Article article);
}
