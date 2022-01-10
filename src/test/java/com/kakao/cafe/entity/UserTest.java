package com.kakao.cafe.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private User subject;

    @BeforeEach
    void setUp() {
        subject = new User.Builder()
                .id("id")
                .password("password")
                .email("email")
                .name("name")
                .build();
    }

    @ParameterizedTest
    @MethodSource("provideCheckPasswordParameters")
    void checkPassword(String password, boolean ok) {
        boolean result = subject.checkPassword(password);

        assertEquals(ok, result);
    }

    private static Stream<Arguments> provideCheckPasswordParameters() {
        return Stream.of(
                Arguments.of("password", true),
                Arguments.of("password1", false)
        );
    }
}
