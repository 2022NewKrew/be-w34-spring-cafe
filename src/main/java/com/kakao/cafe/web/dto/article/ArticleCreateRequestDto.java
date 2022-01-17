package com.kakao.cafe.web.dto.article;

import lombok.Getter;

@Getter
public class ArticleCreateRequestDto {
    private final String title;
    private final String content;

    public ArticleCreateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
