package com.kakao.cafe.user.domain;

import com.kakao.cafe.util.ErrorCode;
import com.kakao.cafe.util.exception.UserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserIdTest {

    @Test
    @DisplayName("아이디는 NULL일 수 없다.")
    void validateNull() {
        assertThatThrownBy(() -> new UserId(null))
                .isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_NULL_VALUE.getErrorMessage());
    }

    @DisplayName("아이디는 5-20자리 이다.")
    @ParameterizedTest
    @ValueSource(strings = {"abcd", "0123", "ab12", "01 23", "012345678901234567890"})
    void invalidateSize(String testId) {
        assertThatThrownBy(() -> new UserId(testId))
                .isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_USER_ID.getErrorMessage());
    }

    @DisplayName("아이디는 공백을 포함할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"01 23"})
    void invalidateEmpty(String testId) {
        assertThatThrownBy(() -> new UserId(testId))
                .isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_USER_ID.getErrorMessage());
    }

    @DisplayName("아이디는 -와 _를 제외한 특수문자를 사용할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"01*23", "1234@", "1234#", "~1234"})
    void invalidateAsterisk(String testId) {
        assertThatThrownBy(() -> new UserId(testId))
                .isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_USER_ID.getErrorMessage());
    }

    @DisplayName("유효한 아이디 포맷이면 정상 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abcde", "01234", "12_ab", "asdfg-67890123456789"})
    void validFormat(String testId) {
        new UserId(testId);
    }
}