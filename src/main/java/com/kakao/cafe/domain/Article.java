package com.kakao.cafe.domain;

import com.kakao.cafe.dto.article.ArticleCreationDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
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

    public Article(ArticleCreationDto dto) {
        this.userId = dto.getUserId();
        this.title = dto.getTitle();
        this.body = dto.getBody();
        createdAt = LocalDateTime.now();
        views = 0;
    }
}
