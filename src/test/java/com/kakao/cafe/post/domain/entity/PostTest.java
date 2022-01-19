package com.kakao.cafe.post.domain.entity;

import com.kakao.cafe.post.data.PostsData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;

class PostTest {
    @ParameterizedTest
    @DisplayName("잘못된 파라미터가 주어질 때 Post 생성 실패")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getWrongPostParameterStream")
    void failedCreatePost(String title, String content, String writerName) {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Post(title, content, writerName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("검증");
    }

    @ParameterizedTest
    @DisplayName("Comment 추가 성공")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getCommentStream")
    void successAddComment(Comment comment){
        //given
        Post post = PostsData.getPostList().get(0);

        //when
        post.addComment(comment);

        //then
    }
}