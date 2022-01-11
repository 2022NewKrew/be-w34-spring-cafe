package com.kakao.cafe.controller.articles.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class ArticleWriteRequestDto {
    private final String writer;
    private final String title;
    private final String contents;
}
