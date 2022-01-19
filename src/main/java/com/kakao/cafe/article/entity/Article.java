package com.kakao.cafe.article.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Article {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createTime;

    @Builder
    public Article(Long id, String title, String content, LocalDateTime createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }
}
