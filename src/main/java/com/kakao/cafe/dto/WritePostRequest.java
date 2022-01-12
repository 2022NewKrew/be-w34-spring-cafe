package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WritePostRequest {
    @NotBlank
    @Size(min = 5, max = 10)
    private final String userId;
    @NotBlank
    @Size(min = 1, max = 100)
    private final String title;
    @NotNull
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
