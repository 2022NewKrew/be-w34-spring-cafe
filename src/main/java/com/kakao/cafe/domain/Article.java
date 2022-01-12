package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class Article {

    private Long id;
    private User writer;
    private final String title;
    private final String contents;

    public void setId(long id) {
        this.id = id;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
}
