package com.kakao.cafe.domain.dtos;

import javax.validation.constraints.NotBlank;

public class ArticleSaveDto {
    private String writer;
    @NotBlank
    private final String title;
    @NotBlank
    private final String content;

    public ArticleSaveDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
