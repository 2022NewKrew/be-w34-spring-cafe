package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleUpdateDto {
    private String title;
    private String contents;
    private Long articleId;
    private String WriterId;
}
