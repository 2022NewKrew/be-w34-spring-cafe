package com.kakao.cafe.article.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Article {

    private Long id;
    private final String title;
    private final String body;

    public void setId(Long id) {
        this.id = id;
    }
}
