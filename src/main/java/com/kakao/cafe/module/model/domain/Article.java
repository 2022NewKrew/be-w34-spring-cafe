package com.kakao.cafe.module.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class Article {

    private final Long id;
    private final Long authorId;
    private final String title;
    private final String contents;
    private final LocalDateTime created;
    private final Integer viewCount;
    private final Integer commentCount;
}
