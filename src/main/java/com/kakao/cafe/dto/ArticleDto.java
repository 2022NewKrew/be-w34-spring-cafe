package com.kakao.cafe.dto;

public class ArticleDto {
    private final String title;
    private final String writer;
    private final String contents;

    public ArticleDto(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getWritter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }
}
