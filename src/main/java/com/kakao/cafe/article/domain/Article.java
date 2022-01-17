package com.kakao.cafe.article.domain;

import java.time.Instant;

public class Article {

    private final ArticleId id;
    private final String title;
    private final String content;
    private final Instant createdAt;
    
    public Article(ArticleId id, String title, String content, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public ArticleId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
