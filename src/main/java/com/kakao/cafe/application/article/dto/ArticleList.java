package com.kakao.cafe.application.article.dto;

import java.util.List;

public class ArticleList {

    private final List<ArticleListEntry> articleList;

    public ArticleList(List<ArticleListEntry> articleList) {
        this.articleList = articleList;
    }

    public static ArticleList from(List<ArticleListEntry> articleList) {
        return new ArticleList(articleList);
    }

    public List<ArticleListEntry> getArticleList() {
        return articleList;
    }
}
