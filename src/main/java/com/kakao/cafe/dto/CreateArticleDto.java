package com.kakao.cafe.dto;

public class CreateArticleDto {
    private final String writer;
    private final String title;
    private final String contents;

    public CreateArticleDto(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
