package com.kakao.cafe.domain;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class WritePostRequest {

    private static final int INITIAL_ID = 0;
    @NotBlank(message = "제목이 비어있습니다")
    private final String title;
    @NotBlank(message = "내용이 비어있습니다")
    private final String content;

    public WritePostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity(int userId) {
        return new Post(INITIAL_ID, userId, title, content, new Date());
    }
}
