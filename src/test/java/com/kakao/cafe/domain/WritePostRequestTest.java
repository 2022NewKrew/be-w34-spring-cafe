package com.kakao.cafe.domain;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WritePostRequestTest {

    private static final String userId = UUID.randomUUID().toString();
    private final String title = "title";
    private final String content = "content";

    @Test
    @DisplayName("[성공] UserSignupRequest 객체를 생성한다")
    void WritePostRequest() {
        new WritePostRequest(userId, title, content);
    }

    @Test
    @DisplayName("[성공] Entity로 올바르게 변환한다")
    void toEntity() {
        WritePostRequest writePostRequest = new WritePostRequest(userId, title, content);
        Post post_Answer = new Post(0, userId, title, content, null);

        Post post = writePostRequest.toEntity();

        Assertions.assertEquals(post, post_Answer);
    }
}
