package com.kakao.cafe.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Article 테스트")
class ArticleTest {
    private static final int ALLOWED_LENGTH_TITLE = 16;
    private static final int ALLOWED_LENGTH_WRITER = 8;
    private static final int ALLOWED_LENGTH_CONTENTS = 100;

    @DisplayName("올바른 값들로 Article을 생성했을 때 예외를 던지지 않는다.")
    @Test
    void LegalTest() {
        //give
        int id = 1;
        String title = "LegalTitle";
        String writer = "writer";
        String contents = "LegalContents";
        LocalDateTime localDateTime = LocalDateTime.now();
        //when
        //then
        assertThatCode(() -> new Article(1, title, writer, contents)).doesNotThrowAnyException();
        assertThatCode(() -> new Article(1, title, writer, contents, localDateTime)).doesNotThrowAnyException();
    }

    @DisplayName("올바르지 못한 title이 주어졌을때 Article을 생성하면 IllegalArgumentException 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "StringLengthOver" + ALLOWED_LENGTH_TITLE,
                    "StringLengthOver" + ALLOWED_LENGTH_TITLE + "Too",
                    "StringLengthOver" + ALLOWED_LENGTH_TITLE + "Also"
            }
    )
    void illegalTitleTest(String illegalTitle) {
        //give
        int id = 1;
        String writer = "writer";
        String contents = "LegalContents";
        LocalDateTime localDateTime = LocalDateTime.now();
        //when
        //then
        assertThatThrownBy(() -> new Article(id, illegalTitle, writer, contents)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Article(id, illegalTitle, writer, contents, localDateTime)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바르지 못한 writter가 주어졌을때 Article을 생성하면 IllegalArgumentException 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "isString" + ALLOWED_LENGTH_WRITER,
                    "isString" + ALLOWED_LENGTH_WRITER + "Too",
                    "isString" + ALLOWED_LENGTH_WRITER + "Also"
            }
    )
    void illegalWriterTest(String illegalWriter) {
        //give
        int id = 1;
        String title = "LegalTitle";
        String contents = "LegalContents";
        LocalDateTime localDateTime = LocalDateTime.now();
        //when
        //then
        assertThatThrownBy(() -> new Article(id, title, illegalWriter, contents)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Article(id, title, illegalWriter, contents, localDateTime)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바르지 못한 contents가 주어졌을때 Article을 생성하면 IllegalArgumentException 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("getStringOver100Length")
    void illegalContentsTest(String illegalContent) {
        //give
        int id = 1;
        String title = "LegalTitle";
        String writer = "writer";
        LocalDateTime localDateTime = LocalDateTime.now();
        //when
        //then
        assertThatThrownBy(() -> new Article(id, title, writer, illegalContent)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Article(id, title, writer, illegalContent, localDateTime)).isInstanceOf(IllegalArgumentException.class);
    }

    static List<String> getStringOver100Length() {
        return List.of(
                "a".repeat(ALLOWED_LENGTH_CONTENTS + 1),
                "b".repeat(ALLOWED_LENGTH_CONTENTS + 2),
                "c".repeat(ALLOWED_LENGTH_CONTENTS + 3)
        );
    }
}
