package com.kakao.cafe.domain.dtos;

public class ArticleSaveDto {
    private final String writer;
    private final String title;
    private final String content;

    public ArticleSaveDto(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
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
