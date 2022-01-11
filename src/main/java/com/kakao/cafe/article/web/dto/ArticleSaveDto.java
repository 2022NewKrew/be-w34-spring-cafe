package com.kakao.cafe.article.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleSaveDto {

    private String writer;
    private String title;
    private String contents;
}
