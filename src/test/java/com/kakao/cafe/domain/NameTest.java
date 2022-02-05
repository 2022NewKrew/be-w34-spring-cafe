package com.kakao.cafe.user.domain;

import com.kakao.cafe.exception.UserException;
import com.kakao.cafe.util.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @Test
    @DisplayName("이름은 NULL일 수 없다.")
    void validateNull() {
        assertThatThrownBy(() -> new Name(null))
                .isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_NULL_VALUE.getErrorMessage());
    }

    @DisplayName("이름은 1-50자리 이다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "asdfgasdfgasdfgasdfgasdfgasdfgasdfgasdfgasdfgasdfga"})
    void invalidateSize(String testName) {
        assertThatThrownBy(() -> new Name(testName))
                .isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_USER_NAME.getErrorMessage());
    }

    @DisplayName("이름은 특수문자나 숫자를 포함할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "가나다123", "rksk123", "sdfwer!@#$", "!@#$", "!@#$가나다", "1234!@#!$"})
    void invalidateAsteriskAndNumbers(String testName) {
        assertThatThrownBy(() -> new Name(testName))
                .isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_USER_NAME.getErrorMessage());
    }

    @DisplayName("유효한 이름 포맷이면 정상 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"asdf", "가나다", "가나다abc"})
    void validFormat(String testName) {
        new Name(testName);
    }
}