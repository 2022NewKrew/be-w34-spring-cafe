package com.kakao.cafe.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WritePostRequest {
    @NotBlank(message = "아이디가 비어있습니다")
    private final String userId;
    @NotBlank(message = "제목이 비어있습니다")
    private final String title;
    @NotBlank(message = "내용이 비어있습니다")
    private final String content;

    public WritePostRequest(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return new Post(0, userId, title, content, null);
    }
}
