package com.kakao.cafe.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ArticleResponseDTO {
    @NotBlank
    private final Long id;
    @NotBlank
    private final String author;
    @NotBlank
    private final String authorName;
    @NotBlank
    private final String title;
    @NotBlank
    private final String content;
    @NotBlank
    private final LocalDateTime createdAt;
    @NotBlank
    private final LocalDateTime updatedAt;

    @Builder
    public ArticleResponseDTO(Long id, String author, String authorName, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.author = author;
        this.authorName = authorName;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorName() {
        return authorName;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
