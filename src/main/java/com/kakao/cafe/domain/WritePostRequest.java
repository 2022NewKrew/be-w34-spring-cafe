package com.kakao.cafe.domain;

import javax.validation.constraints.NotBlank;

public class WritePostRequest {
    @NotBlank
    private final String userId;
    @NotBlank
    private final String title;
    @NotBlank
    private final String content;

    public WritePostRequest(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return new Post(userId, title, content);
    }
}
