package com.kakao.cafe.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    @DisplayName("사용자 아이디는 영문자와 숫자만으로 이루어져야 한다.")
    void invalidUserIdForm() {

    }

    @Test
    @DisplayName("비밀번호는 8~32자여야 한다.")
    void invalidPasswordLength() {

    }

    @Test
    @DisplayName("이름의 길이는 20글자 이하여야 한다.")
    void invalidLengthOfName() {

    }

    @Test
    @DisplayName("이메일은 표준 이메일 주소 형식을 만족시켜야 한다.")
    void invalidEmailForm() {

    }
}