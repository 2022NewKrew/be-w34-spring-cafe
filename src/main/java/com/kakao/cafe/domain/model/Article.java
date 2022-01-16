package com.kakao.cafe.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Article {
    private final int id;
    private String title;
    private String content;
}
