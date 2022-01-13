package com.kakao.cafe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        this.mockMvc.perform(get("/posts/deleteAll"));
        this.mockMvc.perform(get("/users/deleteAll"));
    }

    @Test
    void postCreateErrorTest() throws Exception {
        this.mockMvc.perform(post("/post")
                .param("title", "제목")
                .param("writer", "yunyul")
                .param("content", "제곧네")).andExpect(status().is4xxClientError());
    }

    @Test
    void postCreateTest() throws Exception {
        this.mockMvc.perform(post("/user/create")
                .param("userId", "yunyul")
                .param("password", "1q2w3e4r")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));

        this.mockMvc.perform(post("/post")
                .param("title", "제목")
                .param("writer", "yunyul")
                .param("content", "제곧네")).andExpect(status().is3xxRedirection());
    }

    @Test
    void postViewTest() throws Exception {
        this.mockMvc.perform(post("/user/create")
                .param("userId", "yunyul")
                .param("password", "1q2w3e4r")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));

        this.mockMvc.perform(post("/post")
                .param("title", "제목")
                .param("writer", "yunyul")
                .param("content", "제곧네"));

        this.mockMvc.perform(get("/post/-1"))
                .andExpect(status().is5xxServerError());
    }
}
