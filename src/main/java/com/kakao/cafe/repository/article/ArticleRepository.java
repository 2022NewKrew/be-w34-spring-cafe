package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Long insert(Article article);
    List<Article> findAll();
    Optional<Article> findById(Long articleId);
}
