package com.kakao.cafe.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailTest {

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_NullAndEmptyValue_ThrowsIllegalArgumentsException(String value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Email(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email", "email@", "email.", "email@.", "email@c", "email.c"})
    void constructor_InValidValue_ThrowsIllegalArgumentsException(String value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Email(value));
    }

    @Test
    void constructor_ValidValue_ReturnsCorrectObject() {
        String value = "testEmail@email.com";

        Email actual = new Email(value);
        assertThat(actual.getValue()).isEqualTo(value);
    }
}
