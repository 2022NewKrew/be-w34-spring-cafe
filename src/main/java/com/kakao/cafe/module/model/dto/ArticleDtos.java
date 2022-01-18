package com.kakao.cafe.module.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class ArticleDtos {

    @Getter
    @RequiredArgsConstructor
    public static class ArticlePostDto {

        @Setter
        private String author;
        @Setter
        private Long authorId;
        private final String title;
        private final String contents;
    }

    @Getter
    @RequiredArgsConstructor
    public static class ArticleListDto {

        private final Long articleId;
        private final String author;
        private final Long authorId;
        private final String title;
        private final LocalDateTime created;
        private final Integer commentCount;
    }

    @Getter
    @RequiredArgsConstructor
    public static class ArticleReadDto {

        private final Long articleId;
        private final String author;
        private final Long authorId;
        private final String title;
        private final LocalDateTime created;
        private final String contents;
        private final Integer commentCount;
    }
}
