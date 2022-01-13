package com.kakao.cafe.domain.article.dao;

import com.kakao.cafe.domain.article.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    Optional<Article> findById(Long id);

    List<Article> findByUserId(String userId);

    List<Article> findAll();
}
