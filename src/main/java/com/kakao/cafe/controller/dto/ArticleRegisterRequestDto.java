package com.kakao.cafe.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleRegisterRequestDto {

    private final String writerId;
    private final String title;
    private final String contents;

}
