package com.kakao.cafe.model.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void isUserIdSame() {
        //give
        UserId userId = new UserId("userId");
        Password password = new Password("password");
        Name name = new Name("name");
        Email email = new Email("email");

        User user = new User(userId, password, name, email);

        UserId testUserId = new UserId("userId");

        //when
        boolean result = user.isUserId(testUserId);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void isUserIdDifferent() {
        //give
        UserId userId = new UserId("userId");
        Password password = new Password("password");
        Name name = new Name("name");
        Email email = new Email("email");

        User user = new User(userId, password, name, email);

        UserId testUserId = new UserId("notSame");

        //when
        boolean result = user.isUserId(testUserId);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void isPasswordSame() {
        //give
        UserId userId = new UserId("userId");
        Password password = new Password("password");
        Name name = new Name("name");
        Email email = new Email("email");

        User user = new User(userId, password, name, email);

        Password testPassword = new Password("password");

        //when
        boolean result = user.isPassword(testPassword);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void isPasswordDifferent() {
        //give
        UserId userId = new UserId("userId");
        Password password = new Password("password");
        Name name = new Name("name");
        Email email = new Email("email");

        User user = new User(userId, password, name, email);

        Password testPassword = new Password("notSame");

        //when
        boolean result = user.isPassword(testPassword);

        //then
        assertThat(result).isFalse();
    }
}
