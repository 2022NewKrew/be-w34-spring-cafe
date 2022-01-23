package com.kakao.cafe.domain;

import lombok.Builder;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class Article {
    @NotBlank
    private final Long id;
    @NotBlank
    private final String author;
    @NotBlank
    private String authorName;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @Nullable
    private List<Reply> replies;
    @NotBlank
    private final LocalDateTime createdAt;
    @Nullable
    private LocalDateTime updatedAt;

    @Builder
    public Article(Long id, String author, String authorName, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Nullable
    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(@Nullable List<Reply> replies) {
        this.replies = replies;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
