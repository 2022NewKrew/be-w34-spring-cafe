package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    void create(Article article);

    List<Article> readAll();

    Optional<Article> read(long id);

    void update(Article article);
}
