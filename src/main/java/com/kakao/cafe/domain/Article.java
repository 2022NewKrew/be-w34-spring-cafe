package com.kakao.cafe.domain;

import com.kakao.cafe.dto.article.ArticleCreationDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Article {
    @Setter
    private long id;
    private final long userId;
    @Setter
    private String title;
    @Setter
    private String body;
    private final LocalDateTime createdAt;
    private final int views;

    public Article(ArticleCreationDTO dto) {
        this.userId = dto.getUserId();
        this.title = dto.getTitle();
        this.body = dto.getBody();
        createdAt = LocalDateTime.now();
        views = 0;
    }

    public Article(long id,
                   long userId,
                   String title,
                   String body,
                   LocalDateTime createdAt,
                   int views) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.views = views;
    }
}
