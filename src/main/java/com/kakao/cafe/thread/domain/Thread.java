package com.kakao.cafe.thread.domain;

import java.time.LocalDateTime;

public class Thread {
    private final Long id;
    private final Long parentId;
    private final Long authorId;
    private final String title;
    private final String content;
    private final String status;
    private final String type;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;

    public Thread(Long id, Long parentId, Long authorId, String title, String content, String status, String type,
                  LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.id = id;
        this.parentId = parentId;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.status = status;
        this.type = type;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getParentId() {
        return parentId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    @Override
    public String toString() {
        return "Thread{" + "id=" + id + ", parentId=" + parentId + ", authorId=" + authorId + ", title='" + title + '\'' + ", content='" + content + '\'' + ", status='" + status + '\'' + ", type='" + type + '\'' + ", createdAt=" + createdAt + ", lastModifiedAt=" + lastModifiedAt + '}';
    }
}
