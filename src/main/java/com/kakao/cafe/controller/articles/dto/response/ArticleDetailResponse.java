package com.kakao.cafe.controller.articles.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class ArticleDetailResponse {
    private final String writer;
    private final String title;
    private final String contents;

    @Builder
    public ArticleDetailResponse(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }
}
