package com.kakao.cafe.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ReplyResponseDTO {
    @NotBlank
    private final Long id;
    @NotBlank
    private final Long articleId;
    @NotBlank
    private final String author;
    @NotBlank
    private final String authorName;
    @NotBlank
    private final String content;
    @NotBlank
    private final LocalDateTime createdAt;
    
    @Builder
    public ReplyResponseDTO(Long id, Long articleId, String author, String authorName, String content, LocalDateTime createdAt) {
        this.id = id;
        this.articleId = articleId;
        this.author = author;
        this.authorName = authorName;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
