package com.kakao.cafe.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class UserNameTest {

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_NullAndEmptyValue_ThrowsIllegalArgumentsException(String value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new UserName(value));
    }

    @Test
    void constructor_ValidValue_ReturnsCorrectObject() {
        String value = "testUserName";

        UserName actual = new UserName(value);
        assertThat(actual.getValue()).isEqualTo(value);
    }
}
