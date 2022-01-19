package com.kakao.cafe.post.application;

import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AddCommentServiceTest {
    @InjectMocks
    private AddCommentService service;

    @Mock
    private PostRepository postRepository;

    @ParameterizedTest
    @DisplayName("댓글 추가 실패")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getCommentStream")
    void failedAddComment(Comment comment){
        //given
        given(postRepository.getPost(any())).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> service.addComment(1000L, comment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("없습니다.");
    }
}