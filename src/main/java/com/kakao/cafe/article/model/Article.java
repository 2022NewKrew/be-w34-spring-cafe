package com.kakao.cafe.article.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class Article {
    private final Long id;
    private final String author;
    private final String title;
    private final String contents;
    private final Date uploadTime;
}
