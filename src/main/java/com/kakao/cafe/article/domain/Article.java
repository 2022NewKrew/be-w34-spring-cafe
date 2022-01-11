package com.kakao.cafe.article.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Article {

    private Long id;
    private final String title;
    private final String body;
    private final String writer;

    @Builder
    public Article(String title, String body, String writer) {
        this.title = title;
        this.body = body;
        this.writer = writer;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
