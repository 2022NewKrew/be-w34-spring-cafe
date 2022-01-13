package com.kakao.cafe.dto;

public class ArticleRequestDto {
    private final String author;
    private final String title;
    private final String content;

    public static ArticleRequestDto of(String author, String title, String content) {
        return new ArticleRequestDto(author, title, content);
    }

    private ArticleRequestDto(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
