package com.kakao.cafe.article.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Article {

    private long id;

    private String title;
    private String contents;
    private String writer;
    private Date createdTime;
    private Date updatedTime;

    public Article(long id, String title, String contents, String writer) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }
}
