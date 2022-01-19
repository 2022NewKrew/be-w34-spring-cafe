package com.kakao.cafe.thread.domain;

import lombok.Builder;

import java.time.LocalDateTime;

public class Post extends Thread {
    private static final ThreadType type = ThreadType.POST;

    @Builder
    public Post(Long id, Long parentId, Long authorId, String title, String content, String status,
                LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        super(id, parentId, authorId, title, content, status, type.name(), createdAt, lastModifiedAt);
    }
}
