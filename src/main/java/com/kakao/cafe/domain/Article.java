package com.kakao.cafe.domain;

import lombok.Getter;

import java.util.Date;

@Getter
public class Article {
    private int id;
    private String title;
    private String content;
    private Date createAt;


}
