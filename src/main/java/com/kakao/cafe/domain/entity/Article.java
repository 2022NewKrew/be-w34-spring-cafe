package com.kakao.cafe.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Article {
    private Long id;
    private Long authorId;
    private String author;
    private String title;
    private String content;
    private Long views;
    private Date createdAt;

}
