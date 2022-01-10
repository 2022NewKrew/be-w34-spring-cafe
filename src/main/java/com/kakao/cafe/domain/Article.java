package com.kakao.cafe.domain;

import lombok.Data;

@Data
public class Article {
    private String writer;
    private String title;
    private String contents;
}
