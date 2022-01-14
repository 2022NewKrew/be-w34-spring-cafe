package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ArticleDto {
    private final String writer;
    private final Date time;
    private final String title;
    private final String contents;

}
