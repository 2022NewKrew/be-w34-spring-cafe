package com.kakao.cafe.domain;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Article {
    private final String id;
    private final String name;
    private final String title;
    private final String contents;

    public Article(String id, String name, String title, String contents) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
    }
}
