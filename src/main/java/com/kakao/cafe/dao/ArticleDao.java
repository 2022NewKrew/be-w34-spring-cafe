package com.kakao.cafe.dao;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ArticleDao {
    private final Long number;
    private final String id;
    private final String name;
    private final String title;
    private final String contents;
    private final Timestamp timestamp;
    
    public ArticleDao(Long number, String id, String name, String title, String contents, Timestamp timestamp) {
        validate(number, id, name, title, contents, timestamp);
        this.number = number;
        this.id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.timestamp = timestamp;
    }

    private void validate(Long number, String id, String name, String title, String contents, Timestamp timestamp) {
    }
}
