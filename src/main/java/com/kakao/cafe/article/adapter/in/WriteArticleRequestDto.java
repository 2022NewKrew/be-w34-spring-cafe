package com.kakao.cafe.article.adapter.in;

public class WriteArticleRequestDto {

    private final String title;
    private final String content;

    public WriteArticleRequestDto(String title, String content) {
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
