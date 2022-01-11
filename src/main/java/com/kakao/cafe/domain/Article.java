package com.kakao.cafe.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public Article(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
