package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostArticleDto {

    private String writer;
    private String contents;
    private String title;
}
