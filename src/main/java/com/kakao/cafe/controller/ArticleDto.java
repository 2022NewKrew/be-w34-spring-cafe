package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ArticleDto {

    private int id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime date;

    public ArticleDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Article toEntity() {
        return new Article(title, content, writer);
    }

    public static ArticleDto from(Article article) {
        ArticleDto articleDto = new ArticleDto(article.getTitle(), article.getContent(), article.getWriter());
        articleDto.setId(article.getId());
        articleDto.setDate(article.getDate());
        return articleDto;
    }
}
