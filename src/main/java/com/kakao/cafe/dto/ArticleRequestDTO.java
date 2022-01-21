package com.kakao.cafe.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class ArticleRequestDTO {
    @NotBlank
    private final String author;
    @NotBlank
    private final String title;
    @NotBlank
    private final String content;

    @Builder
    public ArticleRequestDTO(String author, String title, String content) {
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
