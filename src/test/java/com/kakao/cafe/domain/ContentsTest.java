package com.kakao.cafe.domain;

import com.kakao.cafe.utility.ArticleException;
import com.kakao.cafe.utility.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ContentsTest {

    @Test
    @DisplayName("본문은 null일 수 없다.")
    void invalidNullContents() {
        assertThatThrownBy(() -> new Contents(null))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.INVALID_NULL_VALUE.getErrorMessage());
    }

    @Test
    @DisplayName("본문은 공백일 수 없다.")
    void invalidEmptyContents() {
        assertThatThrownBy(() -> new Contents(""))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.INVALID_ARTICLE_CONTENTS.getErrorMessage());
    }

    @Test
    @DisplayName("올바른 본문이면 정상적으로 생성한다.")
    void validContents() {
        new Contents("정상적인 본문 형식 입니다. \\n 개행 문자가 있어도 됩니다.");
    }
}