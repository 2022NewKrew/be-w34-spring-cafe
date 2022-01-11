package com.kakao.cafe.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Post {

    private final UUID id;
    private final User writer;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    private Post(Builder builder) {
        this.id = UUID.randomUUID();
        this.writer = builder.writer;
        this.title = builder.title;
        this.content = builder.content;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public static class Builder {
        private final User writer;
        private final String title;
        private final String content;

        public Builder(User writer, String title, String content) {
            this.writer = writer;
            this.title = title;
            this.content = content;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
