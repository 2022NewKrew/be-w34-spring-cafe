package com.kakao.cafe.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ArticleListDto {
    private Long articleId;
    private String writer;
    private Date time;
    private String title;
}
