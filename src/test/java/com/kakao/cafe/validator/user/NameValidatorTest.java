package com.kakao.cafe.validator.user;

import com.kakao.cafe.exception.user.InvalidNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NameValidatorTest {
    private final NameValidator validator = new NameValidator();

    @ParameterizedTest
    @DisplayName("이름의 길이는 1~20자 사이여야 한다.")
    @ValueSource(strings = {"charlie.p1", "Chanmin Kim", "김찬민"})
    void validName(String validName) {
        assertDoesNotThrow(() -> validator.validate(validName));
    }

    @ParameterizedTest
    @DisplayName("정상적인 이름 형식을 만족시키지 않으면 예외를 던진다.")
    @ValueSource(strings = {"", "123456789012345678901"})
    void invalidLengthOfName(String invalidName) {
        assertThrows(InvalidNameException.class,
                () -> validator.validate(invalidName));
    }
}