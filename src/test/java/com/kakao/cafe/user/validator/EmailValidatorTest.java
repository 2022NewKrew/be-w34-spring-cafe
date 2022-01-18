package com.kakao.cafe.user.validator;

import com.kakao.cafe.user.exception.InvalidEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailValidatorTest {
    private final EmailValidator validator = new EmailValidator();

    @ParameterizedTest
    @DisplayName("이메일은 표준 이메일 주소 형식을 만족시켜야 한다.")
    @ValueSource(strings = {"charlie.p1@kakaocorp.com", "abc_def@mail.com", "abc.def@mail-archive.com"})
    void validEmailForm(String validEmail) {
        assertDoesNotThrow(() -> validator.isValid(validEmail, null));
    }

    @ParameterizedTest
    @DisplayName("표준 이메일 주소 형식을 만족하지 않으면 예외를 던진다.")
    @ValueSource(strings = {"abc", "abc..def@mail.com", "abc.def@mail..com", "abc.def@mail#archive.com"})
    void invalidEmailForm(String invalidEmail) {
        assertThrows(InvalidEmailException.class,
                () -> validator.isValid(invalidEmail, null));
    }
}