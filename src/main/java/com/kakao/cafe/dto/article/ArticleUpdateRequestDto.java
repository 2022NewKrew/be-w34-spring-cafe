package com.kakao.cafe.dto.article;

public class ArticleUpdateRequestDto {

    private final String title;
    private final String content;

    public ArticleUpdateRequestDto(String title, String content) {
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
