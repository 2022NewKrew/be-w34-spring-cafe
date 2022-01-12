package com.kakao.cafe.application.article.dto;

import com.kakao.cafe.domain.article.Article;
import java.util.List;

public class ArticleList {

    private final List<Article> articleList;

    public ArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public static ArticleList from(List<Article> articleList) {
        return new ArticleList(articleList);
    }

    public List<Article> getArticleList() {
        return articleList;
    }
}
