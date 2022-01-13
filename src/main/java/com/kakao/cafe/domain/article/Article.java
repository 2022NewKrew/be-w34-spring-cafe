package com.kakao.cafe.domain.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Article {
    private Long articleId;
    private String writer;
    private String title;
    private String contents;
}
