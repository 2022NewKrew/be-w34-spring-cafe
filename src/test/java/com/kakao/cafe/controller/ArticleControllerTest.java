package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.SessionUser;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleRepository;
import com.kakao.cafe.domain.user.Profile;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.dto.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class ArticleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ArticleRepository articleRepository;

    String author = "userId";
    Long articleId;
    MockHttpSession authenticatedUserSession;
    MockHttpSession otherUserSession;

    @BeforeEach
    void fixture() {
        User authUser = new User(author, "1234", new Profile("name", "email"));
        User otherUser = new User("다른 사람", "1234", new Profile("name", "email"));
        authenticatedUserSession = new MockHttpSession();
        otherUserSession = new MockHttpSession();
        articleId = articleRepository.save(new Article(author, "글제목", "글내용"));
        authenticatedUserSession.setAttribute("sessionUser", new SessionUser(new UserResponseDto(authUser)));
        otherUserSession.setAttribute("sessionUser", new SessionUser(new UserResponseDto(otherUser)));
    }

    @DisplayName("모든 게시글 조회 테스트")
    @Test
    void getArticlesTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("articles"))
                .andExpect(view().name("index"));
    }

    @DisplayName("단일 게시글 조회 테스트")
    @Test
    void getArticleTest() throws Exception {
        mockMvc.perform(get("/articles/" + articleId)
                        .session(authenticatedUserSession))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("article"))
                .andExpect(view().name("article/show"));
    }

    @DisplayName("단일 게시글 조회 테스트 (인증[세션] 없이 게시글 내용 조회 불가)")
    @Test
    void getArticleTestWithoutSession() throws Exception {
        mockMvc.perform(get("/articles/" + articleId))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("user/login"));
    }

    @DisplayName("게시글 생성 테스트")
    @Test
    void createArticleTest() throws Exception {
        mockMvc.perform(post("/articles")
                        .param("author", author)
                        .param("title", "글제목")
                        .param("content", "글내용")
                        .session(authenticatedUserSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("게시글 생성 테스트 (인증[세션] 없이 게시글 생성 불가)")
    @Test
    void createArticleTestWithoutSession() throws Exception {
        mockMvc.perform(get("/articles/form"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("user/login"));

        mockMvc.perform(post("/articles")
                        .param("author", author)
                        .param("title", "글제목")
                        .param("content", "글내용"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("user/login"));
    }

    @DisplayName("게시글 수정 테스트")
    @Test
    void updateArticleTest() throws Exception {
        mockMvc.perform(put("/articles/" + articleId)
                        .param("author", author)
                        .param("title", "글제목2")
                        .param("content", "글내용2")
                        .session(authenticatedUserSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/articles/" + articleId));
    }

    @DisplayName("게시글 수정 테스트 (인증[세션] 없이 게시글 수정 불가)")
    @Test
    void updateArticleTestWithoutSession() throws Exception {
        mockMvc.perform(get("/articles/" + articleId + "/update"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("user/login"));

        mockMvc.perform(put("/articles/" + articleId)
                        .param("author", author)
                        .param("title", "글제목2")
                        .param("content", "글내용2"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("user/login"));
    }

    @DisplayName("게시글 수정 테스트 (인증[세션]이 글 작성자와 다른 사람일 경우 수정 불가)")
    @Test
    void updateArticleTestWithOtherUserSession() throws Exception {
        mockMvc.perform(get("/articles/" + articleId + "/update")
                        .session(otherUserSession))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("error"));

        mockMvc.perform(put("/articles/" + articleId)
                        .param("author", author)
                        .param("title", "글제목2")
                        .param("content", "글내용2")
                        .session(otherUserSession))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("error"));
    }

    @DisplayName("게시글 삭제 테스트")
    @Test
    void deleteArticleTest() throws Exception {
        mockMvc.perform(delete("/articles/" + articleId)
                        .session(authenticatedUserSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("게시글 삭제 테스트 (인증[세션] 없이 게시글 삭제 불가)")
    @Test
    void deleteArticleTestWithoutSession() throws Exception {
        mockMvc.perform(delete("/articles/" + articleId))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("user/login"));
    }

    @DisplayName("게시글 삭제 테스트 (인증[세션]이 글 작성자와 다른 사람일 경우 삭제 불가)")
    @Test
    void deleteArticleWithOtherUserSession() throws Exception {
        mockMvc.perform(delete("/articles/" + articleId)
                        .session(otherUserSession))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("error"));
    }
}
