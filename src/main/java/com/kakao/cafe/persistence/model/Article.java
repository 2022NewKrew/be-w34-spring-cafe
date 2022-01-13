package com.kakao.cafe.persistence.model;

import java.time.LocalDateTime;
import lombok.ToString;

@ToString
public class Article {

    private final Long id;

    private final String uid;
    private final String title;
    private final String body;

    private final LocalDateTime createdAt;

    public static Article of(String authorUid, String title, String body) {
        return new Article(null, authorUid, title, body, LocalDateTime.now());
    }

    public static Article of(Long id, String uid, String title, String body,
        LocalDateTime createdAt) {
        return new Article(id, uid, title, body, createdAt);
    }

    private Article(Long id, String uid, String title, String body, LocalDateTime createdAt) {
        this.id = id;
        this.uid = uid;
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

    public String getUid() {
        return uid;
    }
}
