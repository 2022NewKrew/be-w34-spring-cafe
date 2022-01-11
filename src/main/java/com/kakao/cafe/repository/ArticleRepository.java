package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article create(Article article);
    List<Article> findAll();
    Optional<Article> findById(Long id);
}
