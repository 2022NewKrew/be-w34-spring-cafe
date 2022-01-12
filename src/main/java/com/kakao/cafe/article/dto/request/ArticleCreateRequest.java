package com.kakao.cafe.article.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleCreateRequest {
    private final String writer;
    private final String title;
    private final String contents;
}
