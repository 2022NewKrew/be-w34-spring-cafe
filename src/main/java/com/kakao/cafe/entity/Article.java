package com.kakao.cafe.entity;

public class Article {
    private final Integer id;
    private final String title;
    private final String content;


    public Article(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
