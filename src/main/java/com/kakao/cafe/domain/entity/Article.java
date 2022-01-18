package com.kakao.cafe.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Article {
    private long id;
    private long authorId;
    private String author;
    private String title;
    private String content;
    private long views;
    private Date createdAt;

}
