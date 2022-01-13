package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    void save(Article article);
    Optional<Article> findByArticleId(Long id);
    List<Article> findAll();
}
