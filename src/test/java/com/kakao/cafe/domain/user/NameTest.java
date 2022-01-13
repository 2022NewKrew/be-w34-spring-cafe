package com.kakao.cafe.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class NameTest {

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_NullAndEmptyValue_ThrowsIllegalArgumentsException(String value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Name(value));
    }

    @Test
    void constructor_ValidValue_ReturnsCorrectObject() {
        String value = "testName";

        Name actual = new Name(value);
        assertThat(actual.getValue()).isEqualTo(value);
    }
}
