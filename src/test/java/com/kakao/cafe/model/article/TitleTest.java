package com.kakao.cafe.model.article;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Title 테스트")
class TitleTest {

    private static final int ALLOWED_LENGTH_TITLE = 16;

    @DisplayName("조건을 만족하지 못한 title이 주어졌을때 IllegalArgumentException 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "StringLengthOver" + ALLOWED_LENGTH_TITLE,
                    "StringLengthOver" + ALLOWED_LENGTH_TITLE + "Too",
                    "StringLengthOver" + ALLOWED_LENGTH_TITLE + "Also"
            }
    )
    public void illegalConstructorTest(String illegalTitle) {
        //give
        //when
        //then
        assertThatThrownBy(() -> new Title(illegalTitle)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @DisplayName("조건을 만족하는 title이 주어졌을때 예외를 던지지 않는다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "LengthUnder" + ALLOWED_LENGTH_TITLE,
                    "LengthUnder" + ALLOWED_LENGTH_TITLE + "Too",
                    "LengthIn" + ALLOWED_LENGTH_TITLE + "Also"
            }
    )
    public void legalConstructorTest(String legalTitle) {
        //give
        //when
        //then
        assertThatCode(() -> new Title(legalTitle)).doesNotThrowAnyException();
    }
}
