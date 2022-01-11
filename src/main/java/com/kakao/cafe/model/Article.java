package com.kakao.cafe.model;

import java.util.Date;

public class Article {
    private final int id;
    private final String title;
    private final String writer;
    private final Date createDate;
    private final String contents;

    public Article(int id, String title, String writer, String contents) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.createDate = new Date();
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return writer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getContents() {
        return contents;
    }
}
