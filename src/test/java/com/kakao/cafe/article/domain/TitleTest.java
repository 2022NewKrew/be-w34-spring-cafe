package com.kakao.cafe.article.domain;

import com.kakao.cafe.util.ErrorCode;
import com.kakao.cafe.util.exception.ArticleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TitleTest {

    @Test
    @DisplayName("제목은 null일 수 없다.")
    void invalidNullTitle() {
        assertThatThrownBy(() -> new Title(null))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.INVALID_NULL_VALUE.getErrorMessage());
    }

    @Test
    @DisplayName("제목은 공백일 수 없다.")
    void invalidEmptyTitle() {
        assertThatThrownBy(() -> new Title(""))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.INVALID_ARTICLE_TITLE.getErrorMessage());
    }

    @ParameterizedTest
    @DisplayName("제목은 개행문자를 포함할 수 없다.")
    @ValueSource(strings = {"\\r", "\\n", "werwer\\rwerwr", "wrwwwrwwrw\\nwewr", "sfwerwfwerw\\r\\n"})
    void invalidNewline(String testTitle) {
        assertThatThrownBy(() -> new Title(testTitle))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.INVALID_ARTICLE_TITLE.getErrorMessage());
    }

    @ParameterizedTest
    @DisplayName("올바른 제목이면 정상적으로 생성한다.")
    @ValueSource(strings = {"안녕하세요. 반갑습니다~!", "Ruby on Rails와 Play가 활성화되기 힘든 이유는 뭘까?"})
    void validTitle(String testTitle) {
        new Title(testTitle);
    }
}
