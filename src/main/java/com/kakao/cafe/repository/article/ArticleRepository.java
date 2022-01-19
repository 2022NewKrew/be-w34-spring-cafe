package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;

import java.util.List;

public interface ArticleRepository {

    Article save(Article article);

    Article findArticle(Long articleId);

    List<Article> findAllArticle();

    Article editArticle(Article article);

    Article deleteArticle(Long articleId);

    void deleteAllArticle();

    boolean isArticleExist(Long articleId);
}
