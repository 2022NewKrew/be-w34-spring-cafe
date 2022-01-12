package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class ArticleDto {
    private int id;
    private String title;
    private String content;
    private Date createAt;

}
