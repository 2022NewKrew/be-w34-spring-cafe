package com.kakao.cafe.article.domain;

import java.time.Instant;

public class Article {

    private final ArticleId id;
    private final String title;
    private final String content;
    private final long createdAt;

    public Article(ArticleId id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = Instant.now().getEpochSecond();
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

    public long getCreatedAt() {
        return createdAt;
    }
}
