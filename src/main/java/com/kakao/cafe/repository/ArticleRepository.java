package com.kakao.cafe.repository;

import com.kakao.cafe.entiry.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Article save(Article article);

    Optional<Article> findById(Long id);

    Optional<Article> findByWriter(String writer);

    List<Article> findAll();

}
