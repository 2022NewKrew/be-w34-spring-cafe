package com.kakao.cafe.dao;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ArticleDao {
    private final Long number;
    private final String writer;
    private final String title;
    private final String contents;
    private final Timestamp timestamp;
    
    public ArticleDao(Long number, String writer, String title, String contents, Timestamp timestamp) {
        validate(number, writer, title, contents, timestamp);
        this.number = number;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.timestamp = timestamp;
    }

    private void validate(Long number, String writer, String title, String contents, Timestamp timestamp) {
    }
}
