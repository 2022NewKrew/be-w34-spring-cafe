package com.kakao.cafe.domain.article;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Article {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime creationTime;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationTime(Timestamp timestamp) {
        creationTime = timestamp.toLocalDateTime();
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
