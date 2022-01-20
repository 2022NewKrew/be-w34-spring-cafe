package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.user.User;
import java.time.LocalDateTime;
import java.util.UUID;

public class Article {

    private final UUID articleId;
    private final Title title;
    private final Content content;
    private final User writer;
    private final LocalDateTime createdAt;
    private final ViewCount viewCount;
    private final ArticleDeleted articleDeleted;

    public Article(UUID articleId, Title title, Content content, User writer, LocalDateTime createdAt, ViewCount viewCount, ArticleDeleted articleDeleted) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
        this.articleDeleted = articleDeleted;
    }

    public Article(Title title, Content content, User writer) {
        this(null, title, content, writer, null, null, null);
    }

    public Article(UUID articleId, Title title, Content content, User writer) {
        this(articleId, title, content, writer, null, null, null);
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

    public ArticleDeleted getArticleDeleted() {
        return articleDeleted;
    }

    public void increaseViewCount() {
        this.viewCount.increase();
    }

    public boolean isWriter(User user) {
        return writer.isUserSame(user);
    }
}
