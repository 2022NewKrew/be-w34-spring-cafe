package com.kakao.cafe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kakao.cafe.dto.ArticleDTO.Result;
import com.kakao.cafe.persistence.model.Article;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.persistence.model.User;
import com.kakao.cafe.persistence.repository.ArticleRepository;
import com.kakao.cafe.persistence.repository.UserRepository;
import java.util.List;
import java.util.Optional;
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
class ArticleControllerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ArticleRepository articleRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("게시글 생성 테스트")
    void create() throws Exception {
        // Given
        when(userRepository.findUserByUid(any()))
            .thenReturn(Optional.of(User.of("uid", "pwd", "name", "email@test.com")));

        // When
        AuthInfo authInfo = AuthInfo.of("uid");

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("title", "title");
        requestParams.add("body", "body");

        ResultActions actions = mockMvc.perform(post("/articles")
            .params(requestParams)
            .sessionAttr("auth", authInfo));

        // Then
        actions
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("인증 정보가 없을 때 게시글 생성 테스트")
    void create2() throws Exception {
        // Given
        when(userRepository.findUserByUid(any()))
            .thenReturn(Optional.of(User.of("uid", "pwd", "name", "email@test.com")));

        // When
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("title", "title");
        requestParams.add("body", "body");

        ResultActions actions = mockMvc.perform(post("/articles")
            .params(requestParams));

        // Then
        actions
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/articles/form-failed"));
    }

    @Test
    @DisplayName("게시글 목록 조회 테스트")
    void readAll() throws Exception {
        // Given
        when(articleRepository.findAllArticles())
            .thenReturn(List.of(Article.of("uid", "title1", "body1"),
                Article.of("uid", "title2", "body2"),
                Article.of("uid", "title3", "body3")));

        // When
        ResultActions actions = mockMvc.perform(get("/"));

        // Then
        List<Result> results = (List<Result>) actions.andReturn()
            .getModelAndView()
            .getModelMap()
            .getAttribute("articles");
        assertThat(results)
            .isNotNull()
            .isInstanceOf(List.class);
        assertThat(results.size())
            .isEqualTo(3);

        actions
            .andExpect(redirectedUrl("/"))
            .andExpect(status().isOk());
        assertThat(actions.andReturn().getModelAndView().getViewName())
            .isNotNull()
            .isEqualTo("index");
    }

    @Test
    @DisplayName("특정 게시글 조회 테스트")
    void read() throws Exception {
        // Given
        when(articleRepository.findArticleById(any()))
            .thenReturn(Optional.of(Article.of("uid", "title", "body")));

        // When
        ResultActions actions = mockMvc.perform(get("/articles/1"));

        // Then
        Result resultDTO = (Result) actions.andReturn()
            .getModelAndView()
            .getModelMap()
            .getAttribute("article");
        assertThat(resultDTO.getTitle())
            .isEqualTo("title");
        assertThat(resultDTO.getBody())
            .isEqualTo("body");

        actions
            .andExpect(status().isOk());
        assertThat(actions.andReturn().getModelAndView().getViewName())
            .isNotNull()
            .isEqualTo("qna/show");
    }
}