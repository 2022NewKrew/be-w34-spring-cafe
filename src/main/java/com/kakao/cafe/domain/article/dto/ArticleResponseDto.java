package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.article.Article;

import java.time.format.DateTimeFormatter;

public class ArticleResponseDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private String createdAt;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm");

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.author = article.getAuthor();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt().format(formatter);
    }

    public Long getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
