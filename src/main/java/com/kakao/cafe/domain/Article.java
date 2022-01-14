package com.kakao.cafe.domain;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class Article {
    private long id;
    private String author;
    private String title;
    private String content;
    private long views;
    private Date createdAt;

//    public Article(long id) {
//        this.id = id;
//        this.createdAt = new Date();
//    }


}
