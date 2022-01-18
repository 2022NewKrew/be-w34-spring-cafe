package com.kakao.cafe.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleCreateRequestDto {

    private final String writer;
    private final String title;
    private final String contents;
}
