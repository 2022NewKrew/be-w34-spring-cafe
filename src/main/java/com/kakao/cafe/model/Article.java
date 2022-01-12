package com.kakao.cafe.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;
import lombok.ToString;

@ToString
public class Article {

    private static final AtomicLong idGenerator;

    private final Long id;

    private final User author;

    private final String title;
    private final String body;

    private final LocalDateTime createdAt;

    static {
        idGenerator = new AtomicLong(1L);
    }

    public static Article of(User author, String title, String body) {
        return new Article(author, title, body, LocalDateTime.now());
    }

    private Article(User author, String title, String body, LocalDateTime createdAt) {
        this.id = idGenerator.getAndIncrement();
        this.author = author;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAuthorName() {
        return author.getName();
    }

    public String getAuthorUserId() {
        return author.getUserId();
    }
}
