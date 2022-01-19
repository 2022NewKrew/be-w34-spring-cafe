package com.kakao.cafe.post.application;

import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.domain.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdatePostServiceTest {
    @InjectMocks
    private UpdatePostService service;

    @Mock
    private PostRepository repository;

    @ParameterizedTest
    @DisplayName("포스트 업데이트 성공")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getPostStream")
    void successUpdatePost(Post post){
        //given
        final String newContent = post.getContent().concat(" good~");

        //when
        service.update(post.getId(), newContent);

        //then
        verify(repository, times(1))
                .update(eq(post.getId()), eq(newContent));
    }
}