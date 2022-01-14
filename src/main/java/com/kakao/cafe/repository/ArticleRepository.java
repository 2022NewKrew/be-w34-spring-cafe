package com.kakao.cafe.repository;

import com.kakao.cafe.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    void save(Article article);

    List<Article> findAll();

    Optional<Article> findOne(Integer id);
}
