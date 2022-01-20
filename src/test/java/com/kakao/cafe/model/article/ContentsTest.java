package com.kakao.cafe.model.article;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Contents 테스트")
class ContentsTest {

    private static final int ALLOWED_LENGTH_CONTENTS = 100;

    @DisplayName("조건을 만족하지 못한 contents가 주어졌을때 IllegalArgumentException 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("getStringOverAllowedLength")
    public void illegalConstructorTest(String illegalContent) {
        //give
        //when
        //then
        assertThatThrownBy(() -> new Contents(illegalContent)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @DisplayName("조건을 만족하는 contents가 주어졌을때 예외를 던지지 않는다.")
    @ParameterizedTest
    @MethodSource("getStringUnderAllowedLength")
    public void legalConstructorTest(String legalContent) {
        //give
        //when
        //then
        assertThatCode(() -> new Contents(legalContent)).doesNotThrowAnyException();
    }

    private static List<String> getStringOverAllowedLength() {
        return List.of(
                "a".repeat(ALLOWED_LENGTH_CONTENTS + 1),
                "b".repeat(ALLOWED_LENGTH_CONTENTS + 2),
                "c".repeat(ALLOWED_LENGTH_CONTENTS + 3)
        );
    }

    private static List<String> getStringUnderAllowedLength() {
        return List.of(
                "a".repeat(ALLOWED_LENGTH_CONTENTS),
                "b".repeat(ALLOWED_LENGTH_CONTENTS - 1),
                "c".repeat(ALLOWED_LENGTH_CONTENTS - 2)
        );
    }
}
