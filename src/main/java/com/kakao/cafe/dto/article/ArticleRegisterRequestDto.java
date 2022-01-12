package com.kakao.cafe.dto.article;

import javax.validation.constraints.NotBlank;

public class ArticleRegisterRequestDto {

    @NotBlank
    private final String userId;
    @NotBlank
    private final String title;
    @NotBlank
    private final String content;

    public ArticleRegisterRequestDto(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
