package com.kakao.cafe.thread.dto;

import javax.validation.constraints.NotBlank;

public class PostCreationForm {
    @NotBlank
    private final String author_username;

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    public String getAuthor_username() {
        return author_username;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public PostCreationForm(String author_username, String title, String content) {
        this.author_username = author_username;
        this.title = title;
        this.content = content;
    }
}
