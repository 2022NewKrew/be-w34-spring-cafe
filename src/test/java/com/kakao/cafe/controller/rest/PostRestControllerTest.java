package com.kakao.cafe.controller.rest;

import com.kakao.cafe.constant.RedirectedURL;
import com.kakao.cafe.domain.Post;
import com.kakao.cafe.dto.post.AddPostDto;
import com.kakao.cafe.dto.post.UpdatePostDto;
import com.kakao.cafe.service.PostService;
import com.kakao.cafe.testutil.post.PostDtoUtil;
import com.kakao.cafe.util.SessionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PostRestControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private HttpSession httpSession;

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
        Long writerId = Long.valueOf(24);
        given(httpSession.getAttribute(SessionUtil.LOGIN_USER_ID)).willReturn(writerId);
        AddPostDto addPostDto = PostDtoUtil.createAddPostDto();

        //When
        postRestController.addPost(addPostDto, response);

        //Then
        then(postService).should(times(1)).addPost(addPostDto, writerId);
        then(response).should(times(1)).sendRedirect(RedirectedURL.AFTER_WRITE_POST);
    }

    @Test
    @DisplayName("게시글 수정 -> 정상")
    void updatePost() throws IOException {
        //Given
        Long writerId = Long.valueOf(235);
        given(httpSession.getAttribute(SessionUtil.LOGIN_USER_ID)).willReturn(writerId);
        UpdatePostDto updatePostDto = PostDtoUtil.createUpdatePostDto();

        //When
        postRestController.updatePost(updatePostDto, response);

        //Then
        then(postService).should(times(1)).updatePost(updatePostDto, writerId);
        then(response).should(times(1)).sendRedirect(RedirectedURL.AFTER_UPDATE_POST);
    }

    @Test
    @DisplayName("게시글 삭제 -> 정상")
    void deletePost() throws IOException {
        //Given
        Long writerId = Long.valueOf(1);
        given(httpSession.getAttribute(SessionUtil.LOGIN_USER_ID)).willReturn(writerId);
        Long targetPostId = Long.valueOf(25);

        //When
        postRestController.deletePost(targetPostId, response);

        //Then
        then(postService).should(times(1)).deleteByIdAndUserId(targetPostId, writerId);
        then(response).should(times(1)).sendRedirect(RedirectedURL.AFTER_DELETE_POST);
    }
}