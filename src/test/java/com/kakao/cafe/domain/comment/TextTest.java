package com.kakao.cafe.domain.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TextTest {

    @Test
    void text_init_null_fail() {
        assertThatThrownBy(() -> new Text(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void text_init_blank_fail(String input) {
        assertThatThrownBy(() -> new Text(input)).isInstanceOf(IllegalArgumentException.class);
    }


}
