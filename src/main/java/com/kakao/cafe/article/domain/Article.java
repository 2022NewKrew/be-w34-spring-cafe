package com.kakao.cafe.article.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class Article {
    private Long id;
    private String title;
    private Long authorId;
    private LocalDateTime date;
    private Integer hits;
    private String contents;

    public void increaseHit() {
        this.hits += 1;
    }
}
