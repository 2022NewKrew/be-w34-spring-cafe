package com.kakao.cafe.domain.article;

import lombok.Data;

@Data
public class Article {
    private Long index;
    private String title;
    private String content;
    private String date;
    private String writer;
    private Long writerId;
    private Long view;
    private boolean deleted = false;
}
