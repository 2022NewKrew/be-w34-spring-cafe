package com.kakao.cafe.domain;

import com.kakao.cafe.domain.member.Password;
import com.kakao.cafe.exception.ErrorMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordTest {

    @DisplayName("포맷에 맞지 않는 비밀번호 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"12345", "12345!", "K12345!", "K12345", "k12345!"})
    void inputPasswordTest(String inputPassword) {
        String errorMessage = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Password(inputPassword);
        }).getMessage();
        assertThat(errorMessage).isEqualTo(ErrorMessages.WRONG_PASSWORD_FORMAT);
    }
}
