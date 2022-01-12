package com.kakao.cafe.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    private static final String userId = "testUserId";
    private static final String title = "testTitle";
    private static final String content = "testUserId";
    private static final String postId = "testPostId";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("[성공] 게시글 작성")
    void write() throws Exception {
        mockMvc.perform(post("/posts")
                        .param("userId", userId)
                        .param("title", title)
                        .param("content", content))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("[성공] 게시글 목록")
    void postList() throws Exception {
        mockMvc.perform(post("/posts")
                .param("userId", userId)
                .param("title", title)
                .param("content", content));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @DisplayName("[성공] 게시글 보기")
    void postById() throws Exception {
        mockMvc.perform(post("/posts")
                .param("userId", userId)
                .param("title", title)
                .param("content", content));

        mockMvc.perform(get("/posts/" + postId))
                .andExpect(status().isOk())
                .andExpect(view().name("posts/show"));
    }
}
