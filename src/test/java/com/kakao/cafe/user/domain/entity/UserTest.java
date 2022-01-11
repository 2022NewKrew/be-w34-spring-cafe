package com.kakao.cafe.user.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class UserTest {
    @ParameterizedTest
    @DisplayName("잘못된 파라미터가 주어질때 User 생성 실패")
    @MethodSource("wrongConstructParameters")
    void failedCreateWhenWrongParameters(String userId, String password, String name, String email){
        assertThatThrownBy(() -> new User(userId, password, name, email))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("검증");
    }

    private static Stream<Arguments> wrongConstructParameters(){
        return Stream.of(
                Arguments.of(null, "asdf1234", "name", "email@daum.net"),
                Arguments.of("us", "asdf1234", "name", "email@daum.net"),
                Arguments.of("userId", "asdf123", "name", "email@daum.net"),
                Arguments.of("userId", "asdf1234", "nameName", "email@daum.net"),
                Arguments.of("userId", "asdf1234", "name", "emaildaum.net")
        );
    }
}