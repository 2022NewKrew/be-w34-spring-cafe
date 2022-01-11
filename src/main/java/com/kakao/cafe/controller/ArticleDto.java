package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class ArticleDto {

    private String title;
    private String content;
    private String writer;
    private Date date;

    public ArticleDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.date = new Date(System.currentTimeMillis());
    }

    public Article toEntity() {
        return new Article(title, content, writer, date);
    }

    public static ArticleDto from(Article article) {
        return new ArticleDto(article.getTitle(), article.getContent(), article.getWriter(), article.getDate());
    }
}
