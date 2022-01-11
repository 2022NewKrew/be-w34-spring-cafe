package com.kakao.cafe.article.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Article {

    private Long id;
    private String writer;
    private String title;
    private String contents;
}
