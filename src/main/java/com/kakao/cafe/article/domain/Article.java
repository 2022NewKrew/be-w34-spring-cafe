package com.kakao.cafe.article.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Article {
    private Long id;
    private String title;
    private Long authorId;
    private LocalDateTime date;
    private Integer hits;
    private String contents;

    @Builder
    private Article(Long id, String title, Long authorId, LocalDateTime date, Integer hits, String contents) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.date = date;
        this.hits = hits;
        this.contents = contents;
    }

    public void increaseHit() {
        this.hits += 1;
    }
}
