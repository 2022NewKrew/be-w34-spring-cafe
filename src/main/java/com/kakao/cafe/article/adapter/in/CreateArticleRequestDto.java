package com.kakao.cafe.article.adapter.in;

public class CreateArticleRequestDto {

    private final String title;
    private final String content;

    public CreateArticleRequestDto(String title, String content) {
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
