package com.kakao.cafe.user.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UserTest {
    @Test
    @DisplayName("정상적인 사용자 아이디, 비밀번호, 이름, 이메일이 주어졌을 때 객체가 생성되어야 한다.")
    void create() {
        String userId = "charlie123";
        String password = "charlie.password.1234";
        String name = "김찬민";
        String email = "charlie.p1@kakaocorp.com";
        assertDoesNotThrow(() -> new User(userId, password, name, email));
    }
}