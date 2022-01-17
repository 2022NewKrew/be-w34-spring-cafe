package com.kakao.cafe.article.application.port.out;

public class CreateArticleDto {

    private final String title;
    private final String content;

    public CreateArticleDto(String title, String content) {
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
