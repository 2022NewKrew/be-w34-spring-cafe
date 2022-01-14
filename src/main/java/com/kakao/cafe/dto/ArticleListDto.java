package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ArticleListDto {
    private String writer;
    private Date time;
    private String title;
}
