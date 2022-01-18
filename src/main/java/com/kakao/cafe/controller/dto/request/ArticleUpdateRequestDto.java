package com.kakao.cafe.controller.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ArticleUpdateRequestDto {
    private final String writerId;
    private final String title;
    private final String contents;

}
