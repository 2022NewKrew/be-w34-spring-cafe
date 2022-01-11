package com.kakao.cafe.domain.article;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Article(String author, String title, String content) {
        this(author, title, content, LocalDateTime.now(), LocalDateTime.now());
    }

    public Article(String author, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
