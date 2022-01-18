package com.kakao.cafe.domain.repository.article;

import com.kakao.cafe.domain.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(Long id);
    List<Article> findAll();
    long countRecords();
}
