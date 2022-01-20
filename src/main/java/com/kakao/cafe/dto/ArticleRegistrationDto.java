package com.kakao.cafe.dto;

import com.kakao.cafe.entity.Article;

import javax.validation.constraints.NotBlank;

public class ArticleRegistrationDto {
    @NotBlank(message = "제목을 입력해주세요")
    private final String title;
    @NotBlank(message = "본문 내용을 입력해주세요")
    private final String content;

    public ArticleRegistrationDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(0, title, content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
