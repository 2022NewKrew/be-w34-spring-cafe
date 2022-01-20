package com.kakao.cafe.domain;

import java.time.LocalDateTime;

public class Reply {

    private int id;
    private String writer;
    private String content;
    private LocalDateTime date;
    private int articleId;

    public Reply(String writer, String content, LocalDateTime date, int articleId) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.date = date;
        this.articleId = articleId;
    }

    public Reply(int id, String writer, String content, LocalDateTime date, int articleId) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.date = date;
        this.articleId = articleId;
    }

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean matchWriter(String writer) {
        return this.writer.equals(writer);
    }
}
