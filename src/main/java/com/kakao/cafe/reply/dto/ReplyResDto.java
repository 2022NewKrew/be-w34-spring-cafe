package com.kakao.cafe.reply.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReplyResDto {
    private Long id;
    private String writer;
    private String comment;
    private String createdAt;

    @Builder
    private ReplyResDto(Long id, String writer, String comment, String createdAt) {
        this.id = id;
        this.writer = writer;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}
