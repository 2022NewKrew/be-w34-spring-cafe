package com.kakao.cafe.dto;

public class ArticleCreateDto {
    private final String title;
    private final String writer;
    private final String contents;

    public ArticleCreateDto(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }
}
