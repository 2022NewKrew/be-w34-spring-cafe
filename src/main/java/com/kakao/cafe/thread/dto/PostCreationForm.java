package com.kakao.cafe.thread.dto;

import javax.validation.constraints.NotBlank;

public class PostCreationForm {
    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    public PostCreationForm(String title, String content) {
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
