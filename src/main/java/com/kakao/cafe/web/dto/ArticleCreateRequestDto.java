package com.kakao.cafe.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ArticleCreateRequestDto {

    private final String writer;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDateTime modifiedAt = LocalDateTime.now();
}
