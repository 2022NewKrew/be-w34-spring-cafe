package com.kakao.cafe.Repository;

import com.kakao.cafe.Domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    void saveArticle(Article article);

    Optional<Article> findByArticleId(Long id);

    Optional<Article> findByTitle(String title);

    List<Article> findAllArticles();

    void editArticle(Long articleId, Article article);

    void deleteArticle(Long articleId);
}
