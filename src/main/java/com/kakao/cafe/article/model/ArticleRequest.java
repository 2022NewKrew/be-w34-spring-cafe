package com.kakao.cafe.article.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleRequest {
    private final String author;
    private final String title;
    private final String contents;
}
