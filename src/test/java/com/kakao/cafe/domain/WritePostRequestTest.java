package com.kakao.cafe.domain;

import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WritePostRequestTest {

    private static final int INITIAL_ID = 0;
    private static final String userId = "userId";
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
        Post post_Answer = new Post(INITIAL_ID, userId, title, content, new Date());

        Post post = writePostRequest.toEntity();

        Assertions.assertEquals(post, post_Answer);
    }
}
