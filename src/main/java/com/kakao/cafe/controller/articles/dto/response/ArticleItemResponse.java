package com.kakao.cafe.controller.articles.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ArticleItemResponse {
    private final Long id;
    private final String writer;
    private final String title;
}
