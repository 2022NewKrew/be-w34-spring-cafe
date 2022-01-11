package com.kakao.cafe.application.article.dto;

public class WriteRequest {

    private final String writer;
    private final String title;
    private final String contents;

    public WriteRequest(String writer, String title, String contents) {
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
