package com.kakao.cafe.dto;

import java.time.LocalDateTime;

public class ArticleResponseDto {
    private final Long id;
    private final String author;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public static ArticleResponseDto of(Long id, String author, String title, String content, LocalDateTime createdAt) {
        return new ArticleResponseDto(id, author, title, content, createdAt);
    }

    private ArticleResponseDto(Long id, String author, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
