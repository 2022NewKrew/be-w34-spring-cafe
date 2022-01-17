package com.kakao.cafe.web.article.domain;

import java.sql.Date;

public class Article {
    private String writer;
    private String title;
    private String content;
    private int id;
    private Date date;

    public String getWriter() { return this.writer; }
    public String getTitle() { return this.title; }
    public String getContent() { return this.content; }
    public int getId() {
        return this.id;
    }
    public Date getDate() { return this.date; }

    public void setWriter(String writer) {
        this.writer = writer;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setDate(Date date) { this.date = date; }
}
