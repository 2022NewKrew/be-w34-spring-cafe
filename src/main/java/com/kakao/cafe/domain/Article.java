package com.kakao.cafe.domain;

import lombok.Builder;

@Builder
public class Article {

    private Long id;
    private final String writer;
    private final String title;
    private final String contents;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
