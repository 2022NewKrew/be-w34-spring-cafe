package com.kakao.cafe.controller;

import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.service.PostService;
import com.kakao.cafe.testutil.post.PostDtoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private ModelAndView mav;

    @BeforeEach
    void setUp() {
        mav = mock(ModelAndView.class);
    }

    @Test
    @DisplayName("게시글 화면 반환 -> 정상, check mav, service")
    void postDetailView_checkMav() {
        //Given
        Long postId = Long.valueOf(24);
        PostViewDto postViewDto = PostDtoUtil.createPostViewDto(postId);
        given(postService.findPostViewDtoById(postId)).willReturn(postViewDto);

        //When
        postController.postDetailView(postId, mav);

        //Then
        then(mav).should(times(1)).addObject("postViewDto", postViewDto);
        then(mav).should(times(1)).setViewName("postDetail");
    }
}