package com.kakao.cafe.service;

import com.kakao.cafe.vo.Article;

import java.util.List;

public interface ArticleService {

    void insertArticle(Article article);

    List<Article> getArticleList();

    Article getArticleByArticleId(String articleId);
}
