package com.kakao.cafe.reply.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyEntity {
    private Long id;
    private Long articleId;
    private String writer;
    private String comment;
    private LocalDateTime createdAt;

    @Builder
    private ReplyEntity(Long id, Long articleId, String writer, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.articleId = articleId;
        this.writer = writer;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}
