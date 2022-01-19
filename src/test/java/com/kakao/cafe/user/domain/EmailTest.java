package com.kakao.cafe.user.domain;

import com.kakao.cafe.util.ErrorCode;
import com.kakao.cafe.util.exception.UserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailTest {
    @Test
    @DisplayName("이메일은 NULL일 수 없다.")
    void validateNull() {
        assertThatThrownBy(() -> new Email(null))
                .isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_NULL_VALUE.getErrorMessage());
    }

    @DisplayName("이메일은 @와 .을 반드시 포함해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"123456@asdfsdf", "12134sdf."})
    void invalidateAsteriskAndNumbers(String testName) {
        assertThatThrownBy(() -> new Email(testName))
                .isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_USER_EMAIL.getErrorMessage());
    }

    @DisplayName("이메일은 @앞, @와 .사이, .뒤에 문자와 숫자가 존재해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"@sdf.com", "wer.wer@.com", "wer@wer."})
    void invalidateSize(String testName) {
        assertThatThrownBy(() -> new Email(testName))
                .isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_USER_EMAIL.getErrorMessage());
    }

    @DisplayName("유효한 이메일 포맷이면 정상 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"as_df123@asdf.com", "awe.we@wert.net"})
    void validFormat(String testName) {
        new Email(testName);
    }
}