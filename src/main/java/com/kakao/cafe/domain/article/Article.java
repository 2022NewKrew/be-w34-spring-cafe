package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.user.User;
import java.time.LocalDateTime;

public class Article {

    ArticleId articleId;
    Title title;
    Content content;
    User writer;
    LocalDateTime createdAt;
    ViewCount viewCount;

    public Article(Title title, Content content, User writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = LocalDateTime.now();
        this.viewCount = new ViewCount();
    }

    public ArticleId getArticleId() {
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

    public void setArticleId(int id) {
        this.articleId = new ArticleId(id);
    }

    public void increaseViewCount() {
        this.viewCount.increase();
    }
}
