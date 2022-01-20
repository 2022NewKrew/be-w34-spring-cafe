package com.kakao.cafe.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PostDetailDto {

    private final UUID id;
    private final String title;
    private final UUID writerId;
    private final String writerName;
    private final String content;
    private final String createdAt;

    public PostDetailDto(UUID id, String title, UUID writerId, String writerName, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.writerId = writerId;
        this.writerName = writerName;
        this.content = content;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public UUID getWriterId() {
        return writerId;
    }
}
