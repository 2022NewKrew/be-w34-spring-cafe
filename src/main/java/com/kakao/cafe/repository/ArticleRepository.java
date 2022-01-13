package com.kakao.cafe.repository;

import com.kakao.cafe.vo.Article;

import java.util.List;

public interface ArticleRepository {

    long insertArticle(Article article);

    List<Article> getAllArticle();

    Article getArticleById(long id);

    int increaseViews(long articleId);
}
