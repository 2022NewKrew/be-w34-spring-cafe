package com.kakao.cafe.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Transactional
    void postCreateErrorTest() throws Exception {
        this.mockMvc.perform(post("/post")
                .param("title", "제목")
                .param("writer", "yunyul")
                .param("content", "제곧네")).andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    void postCreateTest() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();

        mockMvc.perform(post("/user/create")
                .param("userId", "yunyul")
                .param("password", "1q2w3e4r")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));

        mockMvc.perform(post("/users/login")
                .param("id", "yunyul")
                .param("password", "1q2w3e4r"));

        mockMvc.perform(post("/post")
                        .param("title", "제목")
                        .param("writer", "yunyul")
                        .param("content", "제곧네"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @Transactional
    void postCreateNoLoginFail() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();

        mockMvc.perform(post("/user/create")
                .param("userId", "yunyul")
                .param("password", "1q2w3e4r")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));

        mockMvc.perform(post("/post")
                .param("title", "제목")
                .param("writer", "yunyul")
                .param("content", "제곧네")).andExpect(status().isUnauthorized());
    }


    @Test
    @Transactional
    void postViewFailByNotExistsPostId() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();

        mockMvc.perform(post("/user/create")
                .param("userId", "yunyul")
                .param("password", "1q2w3e4r")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));

        mockMvc.perform(post("/users/login")
                .param("id", "yunyul")
                .param("password", "1q2w3e4r"));

        mockMvc.perform(post("/post")
                .param("title", "제목")
                .param("writer", "yunyul")
                .param("content", "제곧네"));

        mockMvc.perform(get("/post/-1"))
                .andExpect(status().is5xxServerError());
    }


    @Test
    @Transactional
    void postViewNoLoginFail() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();

        mockMvc.perform(post("/user/create")
                .param("userId", "yunyul")
                .param("password", "1q2w3e4r")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));

        mockMvc.perform(post("/users/login")
                .param("id", "yunyul")
                .param("password", "1q2w3e4r"));

        mockMvc.perform(post("/post")
                .param("title", "제목")
                .param("writer", "yunyul")
                .param("content", "제곧네"));

        mockMvc.perform(get("/logout"));

        mockMvc.perform(get("/post/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    void updateFromFailByDifferentUser() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();
        mockMvc.perform(post("/users/login")
                .param("id", "javajigi")
                .param("password", "test"));

        mockMvc.perform(get("/questions/2"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    void updateFormSuccess() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();

        mockMvc.perform(post("/users/login")
                .param("id", "javajigi")
                .param("password", "test"));

        mockMvc.perform(get("/questions/1"))
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    void updateFailByWrongUser() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();

        mockMvc.perform(post("/users/login")
                .param("id", "javajigi")
                .param("password", "test"));

        mockMvc.perform(put("/questions/2")
                        .param("title", "WRONG_TITLE")
                        .param("content", "WRONG_CONTENT"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    void updateSuccess() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();

        mockMvc.perform(post("/users/login")
                .param("id", "javajigi")
                .param("password", "test"));

        mockMvc.perform(put("/questions/1")
                        .param("title", "RIGHT_THING")
                        .param("content", "RIGHT_CONTENT"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    @Transactional
    void deleteFailByNoLogin() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();
        mockMvc.perform(delete("/posts/1"))
                .andExpect(view().name("redirect:/users/login"));

    }


    @Test
    @Transactional
    void deleteFailByWrongGuy() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();
        mockMvc.perform(post("/users/login")
                .param("id", "yunyul")
                .param("password", "1q2w3e4r"));
        mockMvc.perform(delete("/posts/2"))
                .andExpect(view().name("redirect:/users/login"));

    }


    @Test
    @Transactional
    void deleteFailByCommentsOfOthers() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();
        mockMvc.perform(post("/users/login")
                .param("id", "javajigi")
                .param("password", "test"));
        mockMvc.perform(delete("/posts/1"))
                .andExpect(status().isBadRequest());

    }

    @Test
    @Transactional
    void deleteSuccess() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();
        mockMvc.perform(post("/users/login")
                .param("id", "javajigi")
                .param("password", "test"));
        mockMvc.perform(delete("/posts/3"))
                .andExpect(status().is3xxRedirection());
    }


    @Test
    @Transactional
    void viewPostCommentTest() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();
        mockMvc.perform(post("/users/login")
                .param("id", "javajigi")
                .param("password", "test"));
        mockMvc.perform(get("/post/1"))
                .andExpect(model().attribute("comments", hasSize(1)
                ));
    }


    private MockMvc getMethodSessionMvc() {
        return mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(sharedHttpSession()) // use this session across requests
                .build();
    }
}
