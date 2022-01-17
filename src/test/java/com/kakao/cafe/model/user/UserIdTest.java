package com.kakao.cafe.model.user;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("UserId 테스트")
class UserIdTest {

    private static final int ALLOWED_LENGTH_USERID = 16;

    @DisplayName("조건을 만족하 못한 userId가 주어졌을 때 IllegalArgumentException을 던진다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "thisStringLengthOver" + ALLOWED_LENGTH_USERID,
                    "thisStringLengthOver" + ALLOWED_LENGTH_USERID + "Too",
                    "thisStringLengthOver" + ALLOWED_LENGTH_USERID + "Also"
            }
    )
    public void illegalConstructorTest(String testUserId) {
        //give
        //when
        //then
        assertThatThrownBy(() -> new UserId(testUserId))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("조건을 만족하는 userId가 주어졌을 때 예외를 던지지 않는다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "LengthUnder" + ALLOWED_LENGTH_USERID,
                    "LengthUnder" + ALLOWED_LENGTH_USERID + "Too",
                    "LengthIn" + ALLOWED_LENGTH_USERID + "Also"
            }
    )
    public void legalConstructorTest(String testUserId) {
        //give
        //when
        //then
        assertThatCode(() -> new UserId(testUserId))
                .doesNotThrowAnyException();
    }
}
