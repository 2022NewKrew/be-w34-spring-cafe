package com.kakao.cafe.user.validator;

import com.kakao.cafe.user.exception.InvalidUserIdException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserIdValidatorTest {
    private final UserIdValidator validator = new UserIdValidator();

    @ParameterizedTest
    @DisplayName("사용자 아이디는 영어 소문자와 숫자로만 이루어져 있고, 길이는 1~20자 사이여야 한다.")
    @ValueSource(strings = {"charlie123", "123master"})
    void validUserId(String validUserId) {
        assertDoesNotThrow(() -> validator.validate(validUserId));
    }

    @ParameterizedTest
    @DisplayName("정상적인 사용자 아이디의 형식을 만족시키지 않으면 예외를 던진다.")
    @ValueSource(strings = {"", "Charlie123", "김찬민", "*&E(#+@", "123456789012345678901"})
    void invalidLengthOfUserId(String invalidUserId) {
        assertThrows(InvalidUserIdException.class,
                () -> validator.validate(invalidUserId));
    }
}