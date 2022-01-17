package com.kakao.cafe.article.application.port.in;

public class WriteArticleDto {

    private final String title;
    private final String content;

    public WriteArticleDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
