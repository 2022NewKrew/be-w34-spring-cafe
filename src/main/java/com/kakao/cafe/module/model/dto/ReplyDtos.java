package com.kakao.cafe.module.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReplyDtos {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReplyDto {

        private Long id;
        private Long articleId;
        private Long authorId;
        private LocalDateTime created;
        private String comment;
    }

    @Getter
    @AllArgsConstructor
    public static class ReplyPostDto {

        private final String comment;
    }

    @Getter
    @AllArgsConstructor
    public static class ReplyReadDto {

        private final Long replyId;
        private final Long articleId;
        private final Long authorId;
        private final String author;
        private final LocalDateTime created;
        private final String comment;
    }
}
