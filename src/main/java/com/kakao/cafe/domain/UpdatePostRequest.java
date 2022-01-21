package com.kakao.cafe.domain;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class UpdatePostRequest {

    @NotBlank(message = "제목이 비어있습니다")
    private final String title;
    @NotBlank(message = "내용이 비어있습니다")
    private final String content;

    public UpdatePostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity(int userId, int postId) {
        return new Post(postId, userId, title, content, new Date());
    }
}
