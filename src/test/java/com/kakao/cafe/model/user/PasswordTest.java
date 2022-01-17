package com.kakao.cafe.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PasswordTest {
    private static final int ALLOWED_LENGTH_PASSWORD = 16;

    @DisplayName("조건을 만족하지 못한 password가 주어졌을 때 IllegalArgumentException을 던진다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "thisStringLengthOver" + ALLOWED_LENGTH_PASSWORD,
                    "thisStringLengthOver" + ALLOWED_LENGTH_PASSWORD + "Too",
                    "thisStringLengthOver" + ALLOWED_LENGTH_PASSWORD + "Also"
            }
    )
    public void illegalConstructorTest(String testPassword) {
        //give
        //when
        //then
        assertThatThrownBy(() -> new Password(testPassword))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("조건을 만족하는 password가 주어졌을 때 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "LengthUnder" + ALLOWED_LENGTH_PASSWORD,
                    "LengthUnder" + ALLOWED_LENGTH_PASSWORD + "Too",
                    "LengthIn" + ALLOWED_LENGTH_PASSWORD + "Also"
            }
    )
    public void legalConstructorTest(String testPassword) {
        //give
        //when
        //then
        assertThatCode(() -> new Password(testPassword))
                .doesNotThrowAnyException();
    }
}
