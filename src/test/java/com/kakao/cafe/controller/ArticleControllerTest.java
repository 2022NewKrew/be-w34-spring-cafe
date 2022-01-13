package com.kakao.cafe.controller;

import com.kakao.cafe.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("모든 글 조회 테스트")
    void getAllQuestions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getQuestionsInfo() throws Exception {

    }

    @Test
    @DisplayName("글 저장 테스트")
    void addQuestions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/articles")
                        .param("writer", "testWriter")
                        .param("title", "testTitle")
                        .param("contents", "testContents"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        articleRepository.deleteByWriter("testWriter");
    }

    @Test
    @DisplayName("글 작성 화면으로 이동 테스트")
    void write() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/articles/write"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}