package com.kakao.cafe.domain.article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class TitleTest {

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_NullAndEmptyValue_ThrowsIllegalArgumentsException(String value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Title(value));
    }

    @Test
    void constructor_ValidValue_ReturnsCorrectObject() {
        String value = "testTitle";

        Title actual = new Title(value);
        assertThat(actual.getValue()).isEqualTo(value);
    }
}
