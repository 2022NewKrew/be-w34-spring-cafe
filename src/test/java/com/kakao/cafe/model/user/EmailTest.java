package com.kakao.cafe.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailTest {
    private static final int ALLOWED_LENGTH_EMAIL = 24;

    @DisplayName("조건을 만족하지 못한 email이 주어졌을 때 IllegalArgumentException을 던진다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "thisStringSizeIsOverThe" + ALLOWED_LENGTH_EMAIL,
                    "thisStringSizeIsOverThe" + ALLOWED_LENGTH_EMAIL + "Too",
                    "thisStringSizeIsOverThe" + ALLOWED_LENGTH_EMAIL + "Also"
            }
    )
    public void illegalConstructorTest(String testEmail) {
        //give
        //when
        //then
        assertThatThrownBy(() -> new Email(testEmail))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("조건을 만족하는 email이 주어졌을 때 예외를 던지지 않는다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "isLegalString" + ALLOWED_LENGTH_EMAIL,
                    "isLegalString" + ALLOWED_LENGTH_EMAIL + "Too",
                    "isLegalString" + ALLOWED_LENGTH_EMAIL + "Also"
            }
    )
    public void legalConstructorTest(String testEmail) {
        //give
        //when
        //then
        assertThatCode(() -> new Email(testEmail))
                .doesNotThrowAnyException();
    }
}
