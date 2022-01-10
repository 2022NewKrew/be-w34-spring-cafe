package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    Optional<Article> findArticle(Long articleId);

    List<Article> findAllArticle();

    void deleteArticle(Long articleId);

    void deleteAllArticle();
}
