package com.kakao.cafe.web.dto;

public class ArticleCreateRequestDto {

    private final String writer;
    private final String title;
    private final String contents;

    public ArticleCreateRequestDto(String writer, String title, String contents) {
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
