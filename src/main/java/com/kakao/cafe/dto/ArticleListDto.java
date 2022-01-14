package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleListDto {

    private Long articleId;
    private Long writerId;
    private String title;
    private String writer;
    private String time;
}
