package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleDto {
    private int id;
    private String writer;
    private String title;
    private String contents;
}
