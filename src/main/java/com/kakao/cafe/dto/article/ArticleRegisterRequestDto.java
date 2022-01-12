package com.kakao.cafe.dto.article;

import javax.validation.constraints.NotBlank;

public class ArticleRegisterRequestDto {

    @NotBlank
    private final String userName;
    @NotBlank
    private final String title;
    @NotBlank
    private final String content;

    public ArticleRegisterRequestDto(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
