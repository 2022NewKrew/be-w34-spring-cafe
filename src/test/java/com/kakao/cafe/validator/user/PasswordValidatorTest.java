package com.kakao.cafe.validator.user;

import com.kakao.cafe.exception.user.InvalidPasswordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordValidatorTest {
    private final PasswordValidator validator = new PasswordValidator();

    @ParameterizedTest
    @DisplayName("비밀번호는 8~32자여야 한다.")
    @ValueSource(strings = {"test1234", "rea1pa55word!@!"})
    void validPasswordLength(String validPassword) {
        assertDoesNotThrow(() -> validator.validate(validPassword));
    }

    @ParameterizedTest
    @DisplayName("정상적인 비밀번호 형식을 만족시키지 않으면 예외를 던진다.")
    @ValueSource(strings = {"", "1234", "123456789012345678901234567890123"})
    void invalidPasswordLength(String invalidPassword) {
        assertThrows(InvalidPasswordException.class,
                () -> validator.validate(invalidPassword));
    }
}