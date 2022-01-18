package com.kakao.cafe.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    private final int id = 0;
    private final String userId = "userId";
    private final String password = "password";
    private final String name = "name";
    private final String email = "email";

    @Test
    @DisplayName("[성공] User 클래스 생성")
    void User() {
        new User(id, userId, password, name, email);
    }

    @Test
    @DisplayName("[성공] 유저 아이디를 잘 가져와야 한다")
    void getUserId() {
        User user = new User(id, userId, password, name, email);

        Assertions.assertEquals(userId, user.getUserId());
    }

    @Test
    @DisplayName("[성공] 이름을 잘 가져와야 한다")
    void getUserName() {
        User user = new User(id, userId, password, name, email);

        Assertions.assertEquals(name, user.getUserName());
    }
}
