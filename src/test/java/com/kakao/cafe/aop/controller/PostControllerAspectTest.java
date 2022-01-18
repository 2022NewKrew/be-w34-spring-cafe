package com.kakao.cafe.aop.controller;

import com.kakao.cafe.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PostControllerAspectTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostControllerAspect postControllerAspect;

    @Test
    @DisplayName("특정 게시글 조회수 증가 -> 정상")
    void increaseViewNumOfPost() {
        //Given
        Long postId = Long.valueOf(1);

        //When
        postControllerAspect.increaseViewNumOfPost(postId);

        //Then
        then(postService).should(times(1)).increaseViewNumById(postId);
    }
}