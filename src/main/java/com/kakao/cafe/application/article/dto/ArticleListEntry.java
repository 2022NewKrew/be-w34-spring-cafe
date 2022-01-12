package com.kakao.cafe.application.article.dto;

public class ArticleListEntry {

    private final int id;
    private final String writer;
    private final String title;
    private final String createdAt;

    public ArticleListEntry(int id, String writer, String title, String createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
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

    public String getCreatedAt() {
        return createdAt;
    }
}
