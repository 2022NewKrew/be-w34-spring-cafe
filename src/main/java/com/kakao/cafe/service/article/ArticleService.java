package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;

import java.util.List;

public interface ArticleService {

    List<Article> inquireAllArticles();

    void postArticle(Article member);

    Article inquireOneArticle(Long articleId);

    void editArticle(Long articleId, Article article);

    void deleteArticle(Long articleId);

    void deleteAllArticles();
}
