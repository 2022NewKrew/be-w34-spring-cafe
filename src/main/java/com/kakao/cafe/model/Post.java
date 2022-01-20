package com.kakao.cafe.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Post {

    private final UUID id;
    private final UUID writerId;
    private String title;
    private String content;
    private final Timestamp createdAt;
    private boolean deleted;

    private Post(Builder builder) {
        this.id = builder.id;
        this.writerId = builder.writerId;
        this.title = builder.title;
        this.content = builder.content;
        this.createdAt = builder.createdAt;
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

    public Timestamp getCreatedAt() {
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

        private final UUID id;
        private final UUID writerId;
        private final String title;
        private final String content;
        private final Timestamp createdAt;
        private final boolean deleted;

        public Builder(UUID writerId, String title, String content) {
            this.id = UUID.randomUUID();
            this.writerId = writerId;
            this.title = title;
            this.content = content;
            this.createdAt = Timestamp.valueOf(LocalDateTime.now());
            this.deleted = false;
        }

        public Builder(UUID id, UUID writerId, String title, String content) {
            this.id = id;
            this.writerId = writerId;
            this.title = title;
            this.content = content;
            this.createdAt = Timestamp.valueOf(LocalDateTime.now());
            this.deleted = false;
        }

        public Builder(UUID id, UUID writerId, String title, String content, Timestamp createdAt, boolean deleted) {
            this.id = id;
            this.writerId = writerId;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
            this.deleted = deleted;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
