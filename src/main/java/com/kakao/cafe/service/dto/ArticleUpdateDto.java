package com.kakao.cafe.service.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ArticleUpdateDto {

    private final Long id;
    private final String title;
    private final String contents;
}
