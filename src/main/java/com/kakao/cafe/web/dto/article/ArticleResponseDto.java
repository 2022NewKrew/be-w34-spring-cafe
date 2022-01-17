package com.kakao.cafe.web.dto.article;

import lombok.Builder;
import lombok.Getter;

@Getter

public class ArticleResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String date;

    @Builder
    public ArticleResponseDto(Long id, String title, String content, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
