package com.kakao.cafe.domain;

import com.kakao.cafe.domain.member.Email;
import com.kakao.cafe.exception.ErrorMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EmailTest {

    @ParameterizedTest
    @DisplayName("이메일 포멧 테스트")
    @ValueSource(strings = {"aaaaaa", "aaaaaa@bbbbbb", "aaa.aaa@bbbbb", "aaaabbb.bbb"})
    void wrongEmailFormatTest(String inputEmail) {

        String errorMessage = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Email(inputEmail);
        }).getMessage();
        assertThat(errorMessage).isEqualTo(ErrorMessages.WRONG_EMAIL_FORMAT);
    }
}
