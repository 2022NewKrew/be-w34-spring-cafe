package com.kakao.cafe.controller.rest;

import com.kakao.cafe.constant.RedirectedURL;
import com.kakao.cafe.dto.post.AddPostDto;
import com.kakao.cafe.service.PostService;
import com.kakao.cafe.testutil.post.PostDtoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PostRestControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostRestController postRestController;

    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        response = mock(HttpServletResponse.class);
    }

    @Test
    @DisplayName("게시글 추가 -> 정상")
    void addPost() throws IOException {
        //Given
        AddPostDto addPostDto = PostDtoUtil.createAddPostDto();

        //When
        postRestController.addPost(addPostDto, response);

        //Then
        then(postService).should(times(1)).addPost(any(AddPostDto.class), any(Long.class)); // todo : 추후 로그인 구현 시 변경
        then(response).should(times(1)).sendRedirect(RedirectedURL.AFTER_WRITE_POST);
    }
}