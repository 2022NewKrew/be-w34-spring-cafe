package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class Article {

    private Long id;
    private final User writer;
    private final String title;
    private final String contents;

    public void setId(long id) {
        this.id = id;
    }
}
