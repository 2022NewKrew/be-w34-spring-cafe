package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;

import java.util.List;

public interface ArticleRepository {
    public void addArticle(Article article);
    public List<Article> getArticles();
}
