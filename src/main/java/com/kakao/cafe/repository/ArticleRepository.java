package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;

import java.util.List;

public interface ArticleRepository {
    public void addArticle(Article article);
    public List<Article> getArticles();
}
