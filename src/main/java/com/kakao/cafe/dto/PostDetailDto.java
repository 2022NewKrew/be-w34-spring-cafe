package com.kakao.cafe.dto;

import java.sql.Timestamp;
import java.util.UUID;

public class PostDetailDto {
    private final UUID id;
    private final String title;
    private final UUID writerId;
    private final String writerName;
    private final String content;
    private final Timestamp createdAt;

    public PostDetailDto(UUID id, String title, UUID writerId, String writerName, String content, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.writerId = writerId;
        this.writerName = writerName;
        this.content = content;
        this.createdAt = createdAt;
    }

    public UUID getWriterId() {
        return writerId;
    }
}
