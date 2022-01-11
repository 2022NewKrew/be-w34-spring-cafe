package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;

import java.util.List;

public interface ArticleRepository {

    Long save(Article article);

    Article findOne(Long id);

    List<Article> findAll();

    void update(Long id);

}
