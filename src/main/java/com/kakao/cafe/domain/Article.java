package com.kakao.cafe.domain;

import com.kakao.cafe.dto.ArticleDto;
import java.time.Instant;

public class Article {

    private final int id;
    private final String title;
    private final String content;
    private final long createdAt;

    public Article(ArticleDto articleDto) {
        this.id = articleDto.getId();
        this.title = articleDto.getTitle();
        this.content = articleDto.getContent();
        this.createdAt = Instant.now().getEpochSecond();
    }

    public Integer getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
