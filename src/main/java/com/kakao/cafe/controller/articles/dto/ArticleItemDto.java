package com.kakao.cafe.controller.articles.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class ArticleItemDto {
    private final Long id;
    private final String title;
    private final String writer;
}
