package com.kakao.cafe.domain.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Article {
    private Long articleId;
    private String writer;
    private String title;
    private String contents;
    private Boolean deleted;
}
