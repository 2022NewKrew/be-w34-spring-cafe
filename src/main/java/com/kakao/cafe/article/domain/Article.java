package com.kakao.cafe.article.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Article {
    private Long id;
    private String title;
    private Long authorId;
    private String authorStringId;
    private LocalDateTime date;
    private Integer hits;
    private String contents;
    private Boolean isAvailable;

    @Builder
    private Article(Long id, String title, Long authorId, String authorStringId, LocalDateTime date, Integer hits, String contents, Boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.authorStringId = authorStringId;
        this.date = date;
        this.hits = hits;
        this.contents = contents;
        this.isAvailable = isAvailable;
    }

    public void increaseHit() {
        this.hits += 1;
    }
}
