package com.kakao.cafe.service.article.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ArticleInfo {
    private final Long id;
    private final String writer;
    private final String title;
    private final String contents;
}
