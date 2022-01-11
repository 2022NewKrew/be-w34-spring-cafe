package com.kakao.cafe.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
public class ArticleRegisterRequestDto {

    private final String writer;
    private final String title;
    private final String contents;

}
