package com.kakao.cafe.domain.article;

import java.time.LocalDateTime;

public class Article {

    private final int id;
    private final String writer;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;

    public Article(int id, String writer, String title, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
