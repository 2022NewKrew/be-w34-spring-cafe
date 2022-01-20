package com.kakao.cafe.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Post {

    private final UUID id;
    private final UUID writerId;
    private String title;
    private String content;
    private final LocalDateTime createdAt;
    private boolean deleted;

    private Post(Builder builder) {
        this.id = builder.id;
        this.writerId = builder.writerId;
        this.title = builder.title;
        this.content = builder.content;
        this.createdAt = builder.createdAt;
        this.deleted = builder.deleted;
    }

    public static Post copy(Post original) {
        return new Builder(
                original.getWriterId(),
                original.getTitle(),
                original.getContent())
                .id(original.getId())
                .createdAt(original.getCreatedAt())
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

    public boolean isDeleted() {
        return deleted;
    }

    public void update(Post modified) {
        this.title = modified.title;
        this.content = modified.content;
    }

    public void delete() {
        this.deleted = true;
    }

    public static class Builder {

        private UUID id = UUID.randomUUID();
        private final UUID writerId;
        private final String title;
        private final String content;
        private LocalDateTime createdAt = LocalDateTime.now();
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

        public Builder deleted(boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
