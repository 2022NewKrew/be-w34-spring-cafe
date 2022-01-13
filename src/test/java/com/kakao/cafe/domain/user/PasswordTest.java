package com.kakao.cafe.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PasswordTest {

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_NullAndEmptyValue_ThrowsIllegalArgumentsException(String value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Password(value));
    }

    @Test
    void constructor_ValidValue_ReturnsCorrectObject() {
        String value = "testPassword";

        Password actual = new Password(value);
        assertThat(actual.getValue()).isEqualTo(value);
    }
}
