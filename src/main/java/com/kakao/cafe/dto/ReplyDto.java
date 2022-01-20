package com.kakao.cafe.dto;

import com.kakao.cafe.entity.Reply;

import java.time.LocalDateTime;

public class ReplyDto {
    private final String writerName;
    private final String content;
    private final LocalDateTime createdTime;

    public ReplyDto(String writerName, String content, LocalDateTime createdTime) {
        this.writerName = writerName;
        this.content = content;
        this.createdTime = createdTime;
    }

    public static ReplyDto entityToDto(Reply reply) {
        if (reply == null) return null;
        return new ReplyDto(
                reply.getUser().getName(),
                reply.getContent(),
                reply.getCreatedTime()
        );
    }
}
