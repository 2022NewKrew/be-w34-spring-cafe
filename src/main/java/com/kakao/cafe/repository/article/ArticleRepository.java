package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;

import java.util.List;

public interface ArticleRepository {
    void create(Article article);
    List<Article> readAll();
    Article read(long id);
}
