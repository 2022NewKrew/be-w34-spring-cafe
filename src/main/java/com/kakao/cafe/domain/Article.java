package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class Article {
    private final Long articleId;
    private User writer;
    private Date writeTime;
    private Title title;
    private Contents contents;
}
