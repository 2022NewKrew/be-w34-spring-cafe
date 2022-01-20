package com.kakao.cafe.controller.articles.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArticleWriteRequest {
    private final String title;
    private final String contents;

    @Builder
    public ArticleWriteRequest(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
