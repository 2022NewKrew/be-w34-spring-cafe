package com.kakao.cafe.web.article.dto;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Title;

import java.time.LocalDateTime;

public class ArticleListResponse {
    private final int articleId;
    private final Title title;
    private final LocalDateTime createdAt;

    public ArticleListResponse(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.createdAt = article.getCreatedAt();
    }

    public int getArticleId() {
        return articleId;
    }

    public Title getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
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
