package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;

import java.util.List;


public interface ArticleRepository {

    Article save(Article article);

    List<Article> findAll();
}
