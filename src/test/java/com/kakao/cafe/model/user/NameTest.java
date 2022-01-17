package com.kakao.cafe.model.user;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    private static final int ALLOWED_LENGTH_NAME = 8;

    @DisplayName("조건을 만족하지 못한 name이 주어졌을 때 IllegalArgumentException을 던진다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "thisOver" + ALLOWED_LENGTH_NAME,
                    "thisOver" + ALLOWED_LENGTH_NAME + "Too",
                    "thisOver" + ALLOWED_LENGTH_NAME + "Also"
            }
    )
    public void illegalConstructorTest(String testName) {
        //give
        //when
        //then
        assertThatThrownBy(() -> new Name(testName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("조건을 만족하는 name이 주어졌을 때 예외를 던지지 않는다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "under" + ALLOWED_LENGTH_NAME,
                    "length" + ALLOWED_LENGTH_NAME
            }
    )
    public void legalConstructorTest(String testName) {
        //give
        //when
        //then
        assertThatCode(() -> new Name(testName))
                .doesNotThrowAnyException();
    }
}
