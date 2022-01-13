package com.kakao.cafe.web.dto;

public class PostsCreateRequestDto {
    private String title;
    private String content;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public PostsCreateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
