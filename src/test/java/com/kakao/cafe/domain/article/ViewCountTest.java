package com.kakao.cafe.domain.article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ViewCountTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, Integer.MIN_VALUE})
    void constructor_Main_InvalidValue_ThrowsIllegalArgumentsException(int value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new ViewCount(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 15, 2022})
    void constructor_Main_ValidValue_ReturnsCorrectObject(int value) {
        ViewCount actual = new ViewCount(value);
        assertThat(actual.getValue()).isEqualTo(value);
    }

    @Test
    void constructor_Sub_Invoked_ReturnsCorrectObject() {
        ViewCount actual = new ViewCount();
        assertThat(actual.getValue()).isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 15, 2022})
    void increase_Invoked_IncreaseValue(int value) {
        ViewCount viewCount = new ViewCount(value);
        viewCount.increase();
        assertThat(viewCount.getValue()).isEqualTo(value + 1);
    }
}
