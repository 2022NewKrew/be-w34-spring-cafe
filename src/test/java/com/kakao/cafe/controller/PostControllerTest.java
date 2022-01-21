package com.kakao.cafe.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PostControllerTest {

    private static final String userId = "testUserId";
    private static final String title = "testTitle";
    private static final String content = "testUserId";
    MockHttpSession session = new MockHttpSession();

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        String password = "testPassword";
        String name = "testName";
        String email = "testEmail@kakaocorp.com";

        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        mockMvc.perform(post("/users/login")
                        .session(session)
                        .param("userId", userId)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("[성공] 게시글 작성")
    void write() throws Exception {
        mockMvc.perform(post("/posts")
                        .session(session)
                        .param("title", title)
                        .param("content", content))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("[실패] 게시글 작성 - Null 입력")
    @ParameterizedTest(name = "{0}, {1}")
    @CsvSource(value = {"null, content", "title, null", "null, null"}, nullValues = {"null"})
    void write_FailedBy_Null(String title, String content) throws Exception {
        mockMvc.perform(post("/posts")
                        .session(session)
                        .param("title", title)
                        .param("content", content))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/error"));
    }

    @DisplayName("[실패] 게시글 작성 - 빈 문자열")
    @ParameterizedTest(name = "{0}, {1}")
    @CsvSource(value = {"'', content", "title, ''", "'', ''"}, nullValues = {"null"})
    void write_FailedBy_EmptyString(String title, String content) throws Exception {
        mockMvc.perform(post("/posts")
                        .session(session)
                        .param("title", title)
                        .param("content", content))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/error"));
    }

    @Test
    @DisplayName("[성공] 게시글 목록")
    void postList() throws Exception {
        mockMvc.perform(post("/posts")
                        .session(session)
                        .param("title", title)
                        .param("content", content))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("post/list"));
    }

    @Test
    @DisplayName("[성공] 게시글 목록 - 기존 게시글이 없는 경우")
    void postList_By_EmptyPost() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("post/list"));
    }

    @Test
    @DisplayName("[성공] 게시글 보기")
    void postById() throws Exception {
        int postId = 1;

        mockMvc.perform(post("/posts")
                        .session(session)
                        .param("title", title)
                        .param("content", content))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(get("/posts/" + postId)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("post/show"));
    }

    @DisplayName("[실패] 존재하지 않는 게시글 id")
    @ParameterizedTest(name = "id = {0}")
    @ValueSource(ints = {-1, 0, 2, 999})
    void postById_FailedBy_NotExistPostId(int invalidPostId) throws Exception {
        mockMvc.perform(post("/posts")
                        .session(session)
                        .param("title", title)
                        .param("content", content))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(get("/posts/" + invalidPostId)
                        .session(session))
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/error"));
    }
}
