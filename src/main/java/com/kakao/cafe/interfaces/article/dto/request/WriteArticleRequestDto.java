package com.kakao.cafe.interfaces.article.dto.request;

public class WriteArticleRequestDto {
    private final String writer;
    private final String title;
    private final String contents;

    public WriteArticleRequestDto(String writer, String title, String contents) {
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
