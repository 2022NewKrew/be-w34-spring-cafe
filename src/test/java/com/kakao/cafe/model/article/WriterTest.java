package com.kakao.cafe.model.article;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Writer 테스트")
class WriterTest {

    private static final int ALLOWED_LENGTH_WRITER = 8;

    @DisplayName("조건을 만족하지 못한 writter가 주어졌을때 IllegalArgumentException 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "isString" + ALLOWED_LENGTH_WRITER,
                    "isString" + ALLOWED_LENGTH_WRITER + "Too",
                    "isString" + ALLOWED_LENGTH_WRITER + "Also"
            }
    )
    public void illegalConstructorTest(String illegalWriter) {
        //give
        //when
        //then
        assertThatThrownBy(() -> new Writer(illegalWriter)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @DisplayName("조건을 만족하는 writter가 주어졌을때 예외를 던지지 않는다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "isUnder" + ALLOWED_LENGTH_WRITER,
                    "isIn" + ALLOWED_LENGTH_WRITER + "Too",
            }
    )
    public void legalConstructorTest(String legalWriter) {
        //give
        //when
        //then
        assertThatCode(() -> new Writer(legalWriter)).doesNotThrowAnyException();
    }
}
