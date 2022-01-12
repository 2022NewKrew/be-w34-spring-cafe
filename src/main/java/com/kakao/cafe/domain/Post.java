package com.kakao.cafe.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Post {
    private final UUID id;
    private final String userId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public Post(String userId, String title, String content) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}
