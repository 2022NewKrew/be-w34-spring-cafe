package com.kakao.cafe.domain;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Article {
    private final String writer;
    private final String title;
    private final String contents;

    public Article(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }
}
