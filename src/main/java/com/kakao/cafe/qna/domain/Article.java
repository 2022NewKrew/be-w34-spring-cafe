package com.kakao.cafe.qna.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Article {
    @Setter(AccessLevel.NONE)
    private final int id;

    @Setter(AccessLevel.NONE)
    private String writer;

    private String title;
    private String content;
}
