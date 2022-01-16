package com.kakao.cafe.web.article.dto;

import com.kakao.cafe.domain.article.Article;

import java.sql.Timestamp;

public class ArticleListResponse {
    private final int articleId;
    private final String title;
    private final Timestamp createdAt;

    public ArticleListResponse(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.createdAt = article.getCreatedAt();
    }

    public int getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ArticleListResponse)) {
            return false;
        }

        ArticleListResponse articleListResponse = (ArticleListResponse) obj;
        return articleId == articleListResponse.getArticleId() &&
                title.equals(articleListResponse.getTitle()) &&
                createdAt == articleListResponse.getCreatedAt();
    }
}
