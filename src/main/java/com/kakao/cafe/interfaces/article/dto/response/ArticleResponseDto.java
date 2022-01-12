package com.kakao.cafe.interfaces.article.dto.response;

import java.time.LocalDateTime;

public class ArticleResponseDto {
    private final int index;
    private final String writer;
    private final LocalDateTime createdAt;
    private final String title;

    public ArticleResponseDto(int index, String writer, LocalDateTime createdAt, String title) {
        this.index = index;
        this.writer = writer;
        this.createdAt = createdAt;
        this.title = title;
    }
}
