package com.kakao.cafe.application.article.dto;

import java.util.List;

public class ArticleList {

    private final List<ArticleInfo> articleList;

    public ArticleList(List<ArticleInfo> articleList) {
        this.articleList = articleList;
    }

    public static ArticleList from(List<ArticleInfo> articleList) {
        return new ArticleList(articleList);
    }

    public List<ArticleInfo> getValue() {
        return articleList;
    }
}
