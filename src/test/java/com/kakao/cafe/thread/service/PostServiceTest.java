package com.kakao.cafe.thread.service;

import com.kakao.cafe.exception.PostNotFoundException;
import com.kakao.cafe.thread.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postService;

    @Test
    @DisplayName("존재하지 않는 포스트 방문 시 에러 발생해야 함")
    public void givenNonexistentPostId_WhenVisitingPost_ThenThrowPostNotFoundException() {
        // Given
        Long id = 1L;
        given(postRepository.get(id)).willReturn(Optional.empty());

        // When Then
        assertThrows(PostNotFoundException.class, () -> postService.get(id));
    }

}
