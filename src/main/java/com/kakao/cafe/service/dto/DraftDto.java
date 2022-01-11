package com.kakao.cafe.service.dto;

public class DraftDto {

    private final String author;
    private final String title;
    private final String content;

    public DraftDto(String author, String title, String content) {
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
