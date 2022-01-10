package com.kakao.cafe.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    @Test
    @DisplayName("사용자 아이디, 비밀번호, 이름, 이메일로 가입을 할 수 있다.")
    void register() {

    }

    @Test
    @DisplayName("사용자 아이디는 영문자와 숫자만으로 이루어져야 한다.")
    void registerUserIdForm() {

    }

    @Test
    @DisplayName("사용자 아이디는 중복될 수 없다.")
    void registerNoDuplicateUserId() {

    }

    @Test
    @DisplayName("비밀번호는 8~32자여야 한다.")
    void registerPasswordLength() {

    }

    @Test
    @DisplayName("주어진 이메일은 표준 이메일 주소 형식을 만족시켜야 한다.")
    void registerEmailForm() {

    }
}