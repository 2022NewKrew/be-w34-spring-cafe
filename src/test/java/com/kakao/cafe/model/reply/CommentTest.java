package com.kakao.cafe.model.reply;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Comment 테스트")
class CommentTest {

    private static final int ALLOWED_LENGTH_COMMENT = 50;

    @DisplayName("조건을 만족하지 못한 Comment가 주어졌을 때 IllegalArgumentException을 던진다.")
    @ParameterizedTest
    @MethodSource("getStringOverAllowedLength")
    public void illegalConstructorTest(String testComment) {
        //give
        //when
        //then
        assertThatThrownBy(() -> new Comment(testComment))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static List<String> getStringOverAllowedLength() {
        return List.of(
                "a".repeat(ALLOWED_LENGTH_COMMENT + 1),
                "b".repeat(ALLOWED_LENGTH_COMMENT + 2),
                "c".repeat(ALLOWED_LENGTH_COMMENT + 3)
        );
    }

    @DisplayName("조건을 만족하는 Comment가 주어졌을 때 예외를 던지지 않는다.")
    @ParameterizedTest
    @MethodSource("getStringUnderAllowedLength")
    public void legalConstructorTest(String testComment) {
        //give
        //when
        //then
        assertThatCode(() -> new Comment(testComment))
                .doesNotThrowAnyException();
    }

    private static List<String> getStringUnderAllowedLength() {
        return List.of(
                "a".repeat(ALLOWED_LENGTH_COMMENT),
                "b".repeat(ALLOWED_LENGTH_COMMENT - 1),
                "c".repeat(ALLOWED_LENGTH_COMMENT - 2)
        );
    }
}
