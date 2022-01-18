package com.kakao.cafe.dto;

public class ArticleRequestDTO {
    private final String author;
    private final String title;
    private final String content;

    public static ArticleRequestDTO of(String author, String title, String content) {
        return new ArticleRequestDTO(author, title, content);
    }

    private ArticleRequestDTO(String author, String title, String content) {
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
