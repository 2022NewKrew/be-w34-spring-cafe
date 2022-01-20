package com.kakao.cafe.domain.article;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
