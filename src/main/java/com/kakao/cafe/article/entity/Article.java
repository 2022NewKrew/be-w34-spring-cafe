package com.kakao.cafe.article.entity;

public class Article {

    private Integer id;
    private String writer;
    private String title;
    private String contents;

    public Article() {}

    public Article(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Integer getId() { return this.id; }
    public String getWriter() { return this.writer; }
    public String getTitle() { return this.title; }
    public String getContents() { return this.contents; }

    public void setId(Integer id) { this.id = id; }
    public void setWriter(String writer) { this.writer = writer; }
    public void setTitle(String title) { this.title = title; }
    public void setContents(String contents) { this.contents = contents; }
}
