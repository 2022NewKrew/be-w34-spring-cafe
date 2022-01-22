package com.kakao.cafe.domain.reply;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Reply {
    private Long id;
    private Long userId;
    private Long articleId;
    private String content;
    private LocalDateTime creationTime;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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

    public Long getArticleId() {
        return articleId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
