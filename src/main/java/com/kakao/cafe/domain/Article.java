package com.kakao.cafe.domain;

import java.time.LocalDateTime;

public class Article {
    private final String author;
    private final LocalDateTime createdAt;
    private int id;
    private String title;
    private String content;

    public Article(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
