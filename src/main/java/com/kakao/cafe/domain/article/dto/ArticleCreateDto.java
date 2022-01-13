package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.article.Article;

public class ArticleCreateDto {
    private String author;
    private String title;
    private String content;

    public ArticleCreateDto(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Article toEntity() {
        return new Article(null, getAuthor(), getTitle(), getContent(), null);
    }
}
