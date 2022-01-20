package com.kakao.cafe.persistence.article.mysql.dto;

import java.time.LocalDateTime;

public class ArticleTableDto {
    private final int articleId;
    private final String userId;
    private final LocalDateTime articleCreatedAt;
    private final String articleTitle;
    private final String articleContents;

    public ArticleTableDto(int articleId, String userId, LocalDateTime articleCreatedAt, String articleTitle, String articleContents) {
        this.articleId = articleId;
        this.userId = userId;
        this.articleCreatedAt = articleCreatedAt;
        this.articleTitle = articleTitle;
        this.articleContents = articleContents;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getArticleId() {
        return articleId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getArticleCreatedAt() {
        return articleCreatedAt;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleContents() {
        return articleContents;
    }

    public static class Builder {
        private int articleId;
        private String userId;
        private LocalDateTime articleCreatedAt;
        private String articleTitle;
        private String articleContents;

        private Builder() {
        }

        public Builder articleId(int articleId) {
            this.articleId = articleId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder articleCreatedAt(LocalDateTime articleCreatedAt) {
            this.articleCreatedAt = articleCreatedAt;
            return this;
        }

        public Builder articleTitle(String articleTitle) {
            this.articleTitle = articleTitle;
            return this;
        }

        public Builder articleContents(String articleContents) {
            this.articleContents = articleContents;
            return this;
        }

        public ArticleTableDto build() {
            return new ArticleTableDto(articleId, userId, articleCreatedAt, articleTitle, articleContents);
        }
    }
}
