package com.kakao.cafe.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {

    private static final String userId = "testUserId";
    private static final String title = "testTitle";
    private static final String content = "testUserId";

    @Test
    @DisplayName("[성공] User 클래스 생성")
    void Post() {
        new Post(userId, title, content);
    }

    @Test
    @DisplayName("[성공] 게시글 Id를 잘 설정해야 한다")
    void setId() {
        Post post = new Post(userId, title, content);
        int postId = 1;

        post.setId(postId);
    }

    @Test
    @DisplayName("[성공] 게시글 Id를 잘 가져와야 한다")
    void getId() {
        Post post = new Post(userId, title, content);
        int postId = 1;
        post.setId(postId);

        Assertions.assertEquals(postId, post.getId());
    }
}
