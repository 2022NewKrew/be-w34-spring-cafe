package com.kakao.cafe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import com.kakao.cafe.dto.ReplyDTO.Result;
import com.kakao.cafe.dto.ReplyDTO.Update;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.persistence.model.Reply;
import com.kakao.cafe.service.ReplyService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ReplyControllerTest {

    @MockBean
    private ReplyService replyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("댓글 생성 테스트")
    void create() throws Exception {
        // Given

        // When
        AuthInfo authInfo = AuthInfo.of("uid");

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("body", "body");

        ResultActions actions = mockMvc.perform(post("/articles/1/replies")
            .params(requestParams)
            .sessionAttr("auth", authInfo));

        // Then
        actions
            .andExpect(redirectedUrl("/articles/1"));
        then(replyService)
            .should()
            .create(any(), any(), any());
    }

    @Test
    @DisplayName("세션 정보 없이 댓글 생성 테스트")
    void create2() throws Exception {
        // Given

        // When
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("body", "body");

        ResultActions actions = mockMvc.perform(post("/articles/1/replies")
            .params(requestParams));

        // Then
        actions
            .andExpect(redirectedUrl("/error"));
        then(replyService)
            .should(never())
            .create(any(), any(), any());
    }

    @Test
    @DisplayName("게시글 별 댓글 조회 테스트")
    void readAll() throws Exception {
        // Given
        Result replyResult1 = Result.from(
            Reply.builder().id(1L).articleId(1L).uid("uid").userName("name")
                .body("body1").createdAt(LocalDateTime.now()).build());
        Result replyResult2 = Result.from(
            Reply.builder().id(1L).articleId(1L).uid("uid").userName("name")
                .body("body2").createdAt(LocalDateTime.now()).build());
        Result replyResult3 = Result.from(
            Reply.builder().id(1L).articleId(1L).uid("uid").userName("name")
                .body("body3").createdAt(LocalDateTime.now()).build());
        given(replyService.readByArticleId(1L))
            .willReturn(List.of(replyResult1, replyResult2, replyResult3));

        // When
        AuthInfo authInfo = AuthInfo.of("uid");
        ResultActions actions = mockMvc.perform(get("/articles/1/replies")
            .sessionAttr("auth", authInfo));

        // Then
        List<Result> results = (List<Result>) actions.andReturn()
            .getModelAndView()
            .getModelMap()
            .getAttribute("replies");
        assertThat(results.size())
            .isEqualTo(3);
        then(replyService)
            .should()
            .readByArticleId(any());
    }

    @Test
    @DisplayName("댓글 수정 페이지 조회 테스트")
    void editableRead() throws Exception {
        // When
        Result replyResult = Result.from(
            Reply.builder().id(1L).articleId(1L).uid("uid").userName("name").body("body")
                .createdAt(LocalDateTime.now()).build());
        given(replyService.readById(any()))
            .willReturn(replyResult);

        // When
        AuthInfo authInfo = AuthInfo.of("uid");
        ResultActions actions = mockMvc.perform(get("/articles/1/replies/edit/1")
            .sessionAttr("auth", authInfo));

        // Then
        Result result = (Result) actions.andReturn()
            .getModelAndView()
            .getModelMap()
            .getAttribute("reply");
        assertThat(result)
            .isNotNull();
        then(replyService)
            .should()
            .readById(any());
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void update() throws Exception {
        // Given

        // When
        AuthInfo authInfo = AuthInfo.of("uid");
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("body", "body");

        ResultActions actions = mockMvc.perform(put("/articles/1/replies/1")
            .sessionAttr("auth", authInfo)
            .params(requestParams));

        // Then
        actions
            .andExpect(redirectedUrl("/articles/1"));
        then(replyService)
            .should()
            .update(any(), any(), any());
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteTest() throws Exception {
        // Given

        // When
        AuthInfo authInfo = AuthInfo.of("uid");

        ResultActions actions = mockMvc.perform(delete("/articles/1/replies/1")
            .sessionAttr("auth", authInfo));

        // Then
        actions
            .andExpect(redirectedUrl("/articles/1"));
        then(replyService)
            .should()
            .delete(any(), any());
    }
}