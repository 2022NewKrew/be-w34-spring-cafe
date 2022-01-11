package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;

import java.util.List;

public interface ArticleRepository {
    Article create(Article article);
    List<Article> findAll();
}
