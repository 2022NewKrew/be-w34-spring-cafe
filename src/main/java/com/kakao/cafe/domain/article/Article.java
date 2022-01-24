package com.kakao.cafe.domain.article;

import lombok.Data;

@Data
public class Article {
    private Long articleId;
    private String title;
    private String content;
    private String date;
    private String writer;
    private Long writerId;
    private Long view;
    private Boolean deleted = false;
}
