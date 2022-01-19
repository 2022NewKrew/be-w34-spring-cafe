package com.kakao.cafe.domain.repository.article;

import com.kakao.cafe.domain.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);
    Optional<Article> findById(Long id);
    List<Article> findAll();
    void delete(Long id);
    long countRecords();
}
