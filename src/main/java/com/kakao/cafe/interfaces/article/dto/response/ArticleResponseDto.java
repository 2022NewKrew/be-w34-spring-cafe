package com.kakao.cafe.interfaces.article.dto.response;

import java.time.LocalDateTime;

public class ArticleResponseDto {
    private final int index;
    private final String writer;
    private final LocalDateTime createdAt;
    private final String title;
    private final String contents;

    public ArticleResponseDto(int index, String writer, LocalDateTime createdAt, String title, String contents) {
        this.index = index;
        this.writer = writer;
        this.createdAt = createdAt;
        this.title = title;
        this.contents = contents;
    }
}
