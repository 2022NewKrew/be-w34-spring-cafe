package com.kakao.cafe.model;

public class Article {

    private int id;
    private final String writer;
    private final String title;
    private final String contents;

    public Article(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
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

    public void setId(int id) {
        this.id = id;
    }
}
