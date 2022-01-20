package com.kakao.cafe.service.article.dto;

public class ArticleCreateDto {

    private final String title;
    private final String userId;
    private final String contents;

    public ArticleCreateDto(String title, String userId, String contents) {
        this.title = title;
        this.userId = userId;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }

    public String getContents() {
        return contents;
    }
}
