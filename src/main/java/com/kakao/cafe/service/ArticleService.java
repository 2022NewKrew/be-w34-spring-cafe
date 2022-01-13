package com.kakao.cafe.service;

import com.kakao.cafe.vo.Article;

import java.util.List;

public interface ArticleService {

    long insertArticle(Article article);

    List<Article> getArticleList();

    Article getArticleById(long articleId);

    int increaseViews(long articleId);
}
