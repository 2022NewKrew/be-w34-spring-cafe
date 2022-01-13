package com.kakao.cafe.domain.article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class ContentTest {

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_NullAndEmptyValue_ThrowsIllegalArgumentsException(String value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Content(value));
    }

    @Test
    void constructor_ValidValue_ReturnsCorrectObject() {
        String value = "testContent";

        Content actual = new Content(value);
        assertThat(actual.getValue()).isEqualTo(value);
    }
}
