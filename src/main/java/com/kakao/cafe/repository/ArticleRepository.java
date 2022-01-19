package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleRequestDTO;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    void save(ArticleRequestDTO article);
    Optional<Article> findById(Long id);
    List<Article> findAll();
}
