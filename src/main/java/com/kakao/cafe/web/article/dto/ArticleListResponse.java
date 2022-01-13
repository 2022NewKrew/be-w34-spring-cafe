package com.kakao.cafe.web.article.dto;

import com.kakao.cafe.domain.article.Article;

public class ArticleListResponse {
    private final int articleId;
    private final String title;

    public ArticleListResponse(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
    }

    public int getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }
}
