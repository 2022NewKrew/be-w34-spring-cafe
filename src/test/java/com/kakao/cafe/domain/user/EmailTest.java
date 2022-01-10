package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.Email;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EmailTest {
    @Test
    void emailNullTest() {
        assertThatThrownBy(() -> {
            new Email(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void emailZeroLength() {
        assertThatThrownBy(() -> {
            new Email("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void emailBlankTest() {
        assertThatThrownBy(() -> {
            new Email("   ");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"notEmail", "notEmail@", "@notEmail"})
    void emailInvalidFormat(String testCase) {
        assertThatThrownBy(() -> {
            new Email(testCase);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void emailValidFormat() {
        new Email("email@isInvalid.com");
    }
}
