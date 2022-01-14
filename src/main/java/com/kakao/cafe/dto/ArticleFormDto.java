package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticleFormDto {
    private String writer;
    private String title;
    private String contents;
}
