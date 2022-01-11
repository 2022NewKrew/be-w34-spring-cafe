package com.kakao.cafe.domain;

import java.util.Date;

public class Article {

    private int id;
    String title;
    String content;
    String writer;
    Date date;

    public Article(String title, String content, String writer, Date date) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public Date getDate() {
        return date;
    }

    public void generateId(int id) {
        this.id = id;
    }
}
