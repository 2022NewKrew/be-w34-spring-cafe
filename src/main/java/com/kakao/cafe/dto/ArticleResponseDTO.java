package com.kakao.cafe.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ArticleResponseDTO {
    @NotBlank
    private final Long id;
    @NotBlank
    private final String author;
    @NotBlank
    private final String title;
    @NotBlank
    private final String content;
    @NotBlank
    private final LocalDateTime createdAt;

    public static ArticleResponseDTO of(Long id, String author, String title, String content, LocalDateTime createdAt) {
        return new ArticleResponseDTO(id, author, title, content, createdAt);
    }

    private ArticleResponseDTO(Long id, String author, String title, String content, LocalDateTime createdAt) {
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
