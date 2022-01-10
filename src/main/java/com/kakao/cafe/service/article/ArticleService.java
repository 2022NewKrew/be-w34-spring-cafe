package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> findAllArticles();

    void saveArticle(Article member);

    Optional<Article> findOne(Long articleId);

    void editArticle(Long articleId, Article article);

    void deleteArticle(Long articleId);

    void deleteAllArticles();
}
