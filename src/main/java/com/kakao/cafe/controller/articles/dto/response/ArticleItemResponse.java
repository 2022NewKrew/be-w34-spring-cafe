package com.kakao.cafe.controller.articles.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArticleItemResponse {
    private final Long id;
    private final String writer;
    private final String title;

    @Builder
    public ArticleItemResponse(Long id, String writer, String title) {
        this.id = id;
        this.writer = writer;
        this.title = title;
    }
}
