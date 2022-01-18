package com.kakao.cafe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
import java.time.LocalDateTime;
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
        User user = User.builder().uid("uid").password("pwd").name("name").email("email@test.com")
            .build();
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.of(user));

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
            .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("인증 정보가 없을 때 게시글 생성 테스트")
    void create2() throws Exception {
        // Given
        User user = User.builder().uid("uid").password("pwd").name("name").email("email@test.com")
            .build();
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.of(user));

        // When
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("title", "title");
        requestParams.add("body", "body");

        ResultActions actions = mockMvc.perform(post("/articles")
            .params(requestParams));

        // Then
        actions
            .andExpect(redirectedUrl("/error"));
    }

    @Test
    @DisplayName("게시글 목록 조회 테스트")
    void readAll() throws Exception {
        // Given
        Article article1 = Article.builder().uid("uid").title("title1").body("body1")
            .createdAt(LocalDateTime.now()).build();
        Article article2 = Article.builder().uid("uid").title("title2").body("body2")
            .createdAt(LocalDateTime.now()).build();
        Article article3 = Article.builder().uid("uid").title("title3").body("body3")
            .createdAt(LocalDateTime.now()).build();
        given(articleRepository.findAllArticles())
            .willReturn(List.of(article1, article2, article3));

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
    }

    @Test
    @DisplayName("특정 게시글 조회 테스트")
    void read() throws Exception {
        // Given
        Article article = Article.builder().uid("uid").title("title").body("body")
            .createdAt(LocalDateTime.now()).build();
        given(articleRepository.findArticleById(any()))
            .willReturn(Optional.of(article));

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