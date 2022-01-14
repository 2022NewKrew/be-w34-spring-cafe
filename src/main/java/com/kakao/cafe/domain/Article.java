package com.kakao.cafe.domain;

import com.kakao.cafe.dto.article.ArticleCreationDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Article {
    @Setter
    private long id;
    private long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private int views;

    public Article(ArticleCreationDTO dto) {
        this.userId = dto.getUserId();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        createdAt = LocalDateTime.now();
        views = 0;
    }
}
