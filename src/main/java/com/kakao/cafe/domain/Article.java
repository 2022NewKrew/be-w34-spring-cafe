package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Article {
    private int id;
    private String writer;
    private String title;
    private String contents;
}
