package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.user.User;
import java.time.LocalDateTime;
import java.util.UUID;

public class Article {

    private UUID articleId;
    private Title title;
    private Content content;
    private final User writer;
    private LocalDateTime createdAt;
    private ViewCount viewCount;

    public Article(UUID articleId, Title title, Content content, User writer, LocalDateTime createdAt, ViewCount viewCount) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
    }

    public Article(Title title, Content content, User writer) {
        this(null, title, content, writer, null, new ViewCount());
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void increaseViewCount() {
        this.viewCount.increase();
    }

    public void update(Article article) {
        if (this.articleId.equals(article.getArticleId())) {
            title = article.getTitle();
            content = article.getContent();
            viewCount = article.getViewCount();
        }
    }
}
