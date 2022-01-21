package com.kakao.cafe.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Post {

    private final UUID id;
    private final UUID writerId;
    private String title;
    private String content;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private boolean deleted;

    private Post(Builder builder) {
        this.id = builder.id;
        this.writerId = builder.writerId;
        this.title = builder.title;
        this.content = builder.content;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.deletedAt = builder.deletedAt;
        this.deleted = builder.deleted;
    }

    public static Post copy(Post original) {
        return new Builder(
                original.getWriterId(),
                original.getTitle(),
                original.getContent())
                .id(original.getId())
                .createdAt(original.getCreatedAt())
                .updatedAt(original.getUpdatedAt())
                .deletedAt(original.getDeletedAt())
                .deleted(original.isDeleted())
                .build();
    }

    public UUID getId() {
        return id;
    }

    public UUID getWriterId() {
        return writerId;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void update(Post modified) {
        this.title = modified.title;
        this.content = modified.content;
        this.updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }

    public static class Builder {

        private UUID id = UUID.randomUUID();
        private final UUID writerId;
        private final String title;
        private final String content;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();
        private LocalDateTime deletedAt;
        private boolean deleted = false;

        public Builder(UUID writerId, String title, String content) {
            this.writerId = writerId;
            this.title = title;
            this.content = content;
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder deletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public Builder deleted(boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
