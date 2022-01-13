package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.posts.PostEntity;

public class PostsCreateRequestDto {
    private String title;
    private String content;
    private String writer;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public PostEntity toEntity() {
        return new PostEntity(this.title, this.content, this.writer);
    }

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

    public PostsCreateRequestDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
