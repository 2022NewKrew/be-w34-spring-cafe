package com.kakao.cafe.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@SpringBootTest
public class CommentControllerTest {

    private final CommentController commentController;
    private final WebApplicationContext webApplicationContext;

    @Autowired
    public CommentControllerTest(CommentController commentController, WebApplicationContext webApplicationContext) {
        this.commentController = commentController;
        this.webApplicationContext = webApplicationContext;
    }


    @Test
    @Transactional
    void insertFailByNoLogin() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();
        mockMvc.perform(post("/post/1/comment")
                        .param("text", "hello"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    void deleteFailByNoLogin() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();
        mockMvc.perform(delete("/post/1/comment/1"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    @Transactional
    void deleteFailByWrongUserId() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();
        mockMvc.perform(post("/users/login")
                .param("id", "javajigi")
                .param("password", "test"));
        mockMvc.perform(delete("/post/1/comment/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    void deleteSuccess() throws Exception {
        MockMvc mockMvc = getMethodSessionMvc();
        mockMvc.perform(post("/users/login")
                .param("id", "javajigi")
                .param("password", "test"));
        mockMvc.perform(delete("/post/2/comment/2"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    @Transactional
    void updateFailByNoLogin() {

    }

    @Test
    @Transactional
    void updateFailByWrongId() {

    }


    private MockMvc getMethodSessionMvc() {
        return MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(sharedHttpSession()) // use this session across requests
                .build();
    }


}
