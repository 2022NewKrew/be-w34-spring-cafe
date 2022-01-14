package com.kakao.cafe.domain;

import com.kakao.cafe.domain.member.Name;
import com.kakao.cafe.exception.ErrorMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NameTest {

    @Test
    @DisplayName("숫자가 들어간 이름 테스트")
    void wrongFormatNameTest() {
        // given
        String inputName = "루밤123";

        // then
        String errorMessage = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Name(inputName);
        }).getMessage();
        assertThat(errorMessage).isEqualTo(ErrorMessages.WRONG_NAME_FORMAT);
    }
}
