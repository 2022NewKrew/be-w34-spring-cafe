package com.kakao.cafe.controller.articles.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ArticleWriteRequest {
    private final String writer;
    private final String title;
    private final String contents;
}
