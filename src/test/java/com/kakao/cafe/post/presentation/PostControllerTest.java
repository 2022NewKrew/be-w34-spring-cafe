package com.kakao.cafe.post.presentation;

import com.kakao.cafe.ControllerTest;
import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.presentation.dto.CommentRequest;
import com.kakao.cafe.post.presentation.dto.PostDetailDto;
import com.kakao.cafe.post.presentation.dto.PostRequest;
import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.entity.UserInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.ResultActions;

import static com.kakao.cafe.matcher.LambdaMatcher.lambdaMatcher;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends ControllerTest {

    @ParameterizedTest
    @DisplayName("하나의 게시글 상세 정보 가져오기 성공")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getPostStream")
    void getPostDetail(Post post) throws Exception {
        //given
        final MockHttpSession session = createMockSession("userId");
        given(postInfoService.getPost(post.getId())).willReturn(post);

        //when
        final ResultActions actions = mockMvc.perform(
                    get(String.format("/posts/%s", post.getId())
                    ).session(session)
                ).andDo(print());

        //then
        actions.andExpect(status().isOk())
                .andExpect(model().attribute("post", lambdaMatcher((PostDetailDto returnedPost)
                        -> returnedPost.getTitle().equals(post.getTitle())
                                && returnedPost.getId().equals(post.getId())
                )));
    }

    @ParameterizedTest
    @DisplayName("포스트 생성 요청 성공")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getPostRequestStream")
    void successCreatePost(PostRequest postRequest) throws Exception {
        //given
        final User user = getUser();
        final MockHttpSession session = createMockSession(user.getUserId());
        given(searchUserService.getUser(user.getUserId())).willReturn(user);

        //when
        final ResultActions actions = mockMvc.perform(post("/posts")
                .session(session)
                .param("title", postRequest.getTitle())
                .param("content", postRequest.getContent())
        ).andDo(print());

        //then
        actions.andExpect(status().is3xxRedirection());
        verify(writePostService, times(1))
                .save(argThat(post -> post.getTitle().equals(postRequest.getTitle())));
    }

    @ParameterizedTest
    @DisplayName("댓글 달기 성공")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getCommentRequestStream")
    void addComment(CommentRequest commentRequest) throws Exception {
        //given
        final User user = getUser();
        final MockHttpSession session = createMockSession(user.getUserId());
        given(searchUserService.getUser(user.getUserId())).willReturn(user);

        //when
        final ResultActions actions = mockMvc.perform(post("/posts/1001/comment")
                .session(session)
                .param("comment", commentRequest.getComment())
        ).andDo(print());

        //then
        actions.andExpect(status().is3xxRedirection());
        verify(commentService, times(1))
                .addComment(eq(1001L), argThat(comment -> comment.getContent().equals(commentRequest.getComment())));
    }

    private static User getUser(){
        return new User("userId", "pwd12345", new UserInfo("name", "email@naver.com"));
    }

    private static MockHttpSession createMockSession(String userId){
        final MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", userId);
        return session;
    }
}