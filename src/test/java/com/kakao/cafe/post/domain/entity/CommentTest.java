package com.kakao.cafe.post.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

class CommentTest {

    @ParameterizedTest
    @DisplayName("잘못된 파라미터가 주어질 때 Comment 생성 실패")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getWrongCommentParameterStream")
    void validate(String writerName, String comment) {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Comment(writerName, comment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("검증");
    }
}