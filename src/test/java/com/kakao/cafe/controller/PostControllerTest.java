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
    void postCreateTest() throws Exception {
        this.mockMvc.perform(post("/post")
                .param("title", "제목")
                .param("writer", "yunyul")
                .param("content", "제곧네")).andExpect(status().is3xxRedirection());
    }

    /* 현재 해결하지 못한 테스트 케이스 */
    @Test
    void postViewTest() throws Exception {

        this.mockMvc.perform(post("/post")
                .param("title", "제목")
                .param("writer", "yunyul")
                .param("content", "제곧네"));

        /*
            max idx get 하는 api 구현 후에 조치해야 될 것 같다.
         */
        this.mockMvc.perform(get("/post/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("제곧네");

    }
}
