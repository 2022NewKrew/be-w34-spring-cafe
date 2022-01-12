package com.kakao.cafe.domain.article;

import java.time.LocalDateTime;

public class Article {
    private final int id;
    private final String writer;
    private final LocalDateTime createdAt;
    private final String title;
    private final String contents;

    public Article(int id, String writer, LocalDateTime createdAt, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.createdAt = createdAt;
        this.title = title;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
