package com.kakao.cafe.user.domain;

import com.kakao.cafe.util.ErrorCode;
import com.kakao.cafe.util.exception.UserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PasswordTest {

    @Test
    @DisplayName("패스워드는 NULL일 수 없다.")
    void validateNull() {
        assertThatThrownBy(() -> new Password(null))
                .isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_NULL_VALUE.getErrorMessage());
    }

    @DisplayName("패스워드는 8-20자리 이다.")
    @ParameterizedTest
    @ValueSource(strings = {"1234567", "abc123!", "123wert", "0123", ""})
    void invalidSize(String testPassword) {
        assertThatThrownBy(() -> {
            new Password(testPassword);
        }).isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_USER_PASSWORD.getErrorMessage());
    }

    @DisplayName("패스워드는 공백을 포함할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"0123 fghh"})
    void invalidEmpty(String testPassword) {
        assertThatThrownBy(() -> {
            new Password(testPassword);
        }).isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_USER_PASSWORD.getErrorMessage());
    }


    @DisplayName("패스워드는 대소문자, 숫자, 특수문자를 최소 하나씩 포함해야한다.")
    @ParameterizedTest
    @ValueSource(strings = {"asdf1234", "ASBDsdfg", "ASBCD1235", "asdfwerwer!", "QWERWSDFWS!", "@##$12345"})
    void insufficientNumberOfTypes(String testPassword) {
        assertThatThrownBy(() -> {
            new Password(testPassword);
        }).isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.INVALID_USER_PASSWORD.getErrorMessage());
    }

    @DisplayName("유효한 패스워드 포맷이면 정상 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abcde1234#", "!@#123wer", "sdfw!@#123", "ASDF132!@#"})
    void validFormat(String testId) {
        new Password(testId);
    }
}