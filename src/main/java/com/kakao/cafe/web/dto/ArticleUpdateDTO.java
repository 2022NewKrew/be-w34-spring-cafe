package com.kakao.cafe.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ArticleUpdateDTO {
    private long id;
    private String writer;
    private String title;
    private String contents;
    private Timestamp modifiedTime;
}
