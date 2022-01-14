package com.kakao.cafe.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @DisplayName("isCorrectPassword 테스트 - 일치하는 패스워드인 경우 True를 반환")
    @Test
    void isCorrectPassword_CorrectPassword_True() {
        // given
        User user = new User("test", "correct password", "test", "email@daum.net");
        String password = "correct password";

        // when
        boolean result = user.isCorrectPassword(password);

        // then
        Assertions.assertTrue(result);
    }

    @DisplayName("isCorrectPassword 테스트 - 일치하지 않는 패스워드인 경우 False를 반환")
    @Test
    void isCorrectPassword_CorrectPassword_False() {
        // given
        User user = new User("test", "correct password", "test", "test");
        String password = "incorrect password";

        // when
        boolean result = user.isCorrectPassword(password);

        // then
        Assertions.assertFalse(result);
    }

    @DisplayName("updateUserProfile 테스트 - 비밀번호, 이름, 이메일을 변경했을때, 비밀번호 이름 이메일 변경")
    @Test
    void updateUserProfile_changedPasswordAndNameEmail_ChangeAndOriginPasswordAndNameAndEmail() {
        // given
        User user = new User("userId", "password", "name", "email@daum.net");

        User changedUser = new User("userId", "changed password", "changed name", "changedEmail@daum.net");

        // when
        user.updateUserProfile(changedUser);

        // then
        Assertions.assertEquals(changedUser.getName(), user.getName());
        Assertions.assertEquals(changedUser.getEmail(), user.getEmail());
        Assertions.assertEquals(changedUser.getPassword(), user.getPassword());

    }
}
