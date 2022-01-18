package com.kakao.cafe.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user = new User("cih468", "1234", "cih468@naver.com");

    @Test
    void getUserId() {
        assertEquals(user.getUserId(), "cih468");
    }
}