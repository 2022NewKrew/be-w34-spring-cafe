package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.user.User;
import java.time.LocalDateTime;
import java.util.UUID;

public class Article {

    private UUID articleId;
    private final Title title;
    private final Content content;
    private final User writer;
    private final LocalDateTime createdAt;
    private final ViewCount viewCount;

    public Article(UUID articleId, Title title, Content content, User writer, LocalDateTime createdAt, ViewCount viewCount) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
    }

    public Article(Title title, Content content, User writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = LocalDateTime.now();
        this.viewCount = new ViewCount();
    }

    public UUID getArticleId() {
        return articleId;
    }

    public Title getTitle() {
        return title;
    }

    public Content getContent() {
        return content;
    }

    public User getWriter() {
        return writer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ViewCount getViewCount() {
        return viewCount;
    }

    public void setArticleId(UUID id) {
        this.articleId = id;
    }

    public void increaseViewCount() {
        this.viewCount.increase();
    }
}
