package com.kakao.cafe.web.article.domain;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article {
    private int id;
    private String writer;
    private String title;
    private String contents;
    private Timestamp createdTime;
    private Timestamp modifiedTime;
    private boolean deleted;
}
