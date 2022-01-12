package com.kakao.cafe.application.article.dto;

public class ArticleDetail {

    private final int id;
    private final String writer;
    private final String title;
    private final String contents;
    private final String createdAt;

    public ArticleDetail(int id, String writer, String title, String contents, String createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }
}
