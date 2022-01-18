package com.kakao.cafe.web.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ArticleCreateDTO {
    private String writer;
    private String title;
    private String contents;
}
