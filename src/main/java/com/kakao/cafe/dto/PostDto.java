package com.kakao.cafe.dto;

public class PostDto {

    private final String writer;
    private final String title;
    private final String content;

    public PostDto(String writer, String title, String content) {
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
