package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticlePostDto {
    private String writer;
    private String title;
    private String contents;
}
