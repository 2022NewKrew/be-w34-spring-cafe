package com.kakao.cafe.article.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Article {

    private long id;
    private String title;
    private String contents;
    private String writer;
    private boolean active = true;
    private Timestamp createdTime;
    private Timestamp updatedTime;

    public Article() {
    }

    public Article(long id, String title, String contents, String writer) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
//        this.createdTime = Timestamp.valueOf(LocalDateTime.now());
//        this.updatedTime = Timestamp.valueOf(LocalDateTime.now());
    }
}
