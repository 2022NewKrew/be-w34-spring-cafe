package com.kakao.cafe.model;

public class Article {

    private Integer id;
    private String writer;
    private String title;
    private String contents;

    public Article(Integer id, String writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Integer getId() { return id; }

    public String getWriter() {
        return writer;
    }

    public String getTitle() { return title; }

    public String getContents() {
        return contents;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
