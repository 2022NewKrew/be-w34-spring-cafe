package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleShowListDTO {
    long key;
    private String author;
    private String title;
    private String postTime;
}
