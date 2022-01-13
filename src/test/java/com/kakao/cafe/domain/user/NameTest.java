package com.kakao.cafe.domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NameTest {
    @Test
    void nullName() {
        assertThatThrownBy(() -> {
            new Name(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void blankName() {
        assertThatThrownBy(() -> {
            new Name("   ");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void zeroLengthName() {
        assertThatThrownBy(() -> {
            new Name("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
