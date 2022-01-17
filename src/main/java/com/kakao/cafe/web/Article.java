package com.kakao.cafe.web;

public class Article {
    private final String writer;
    private final String title;
    private final String contents;
    private int id;
    private String time;

    public Article(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
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

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
