package com.kakao.cafe.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final Integer articleId = 1;
    private static final String title = "testTitle";
    private static final String content = "testContent";

    @DisplayName("[성공] 게시글 생성")
    @ParameterizedTest(name = "{0}, {1}, {2}")
    @CsvSource(value = {"1, title, content", "2, title2, content2", "3, title3, content3"})
    void write_Article(String articleId, String title, String content) throws Exception{

        mockMvc.perform(post("/articles")
                        .param("articleId", articleId)
                        .param("title", title)
                        .param("content", content))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/articles"));
    }

    @DisplayName("[실패] 게시글 생성 시 제목이나 내용이 Null")
    @ParameterizedTest(name = "{0}, {1}, {2}")
    @CsvSource(value = {"1, null, content", "2, title2, null", "3, null, null"}, nullValues = {"null"})
    void write_Article_Null(String articleId, String title, String content) throws Exception{

        mockMvc.perform(post("/articles")
                        .param("articleId", articleId)
                        .param("title", title)
                        .param("content", content))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("[실패] 게시글 생성 시 제목이나 내용이 Blank")
    @ParameterizedTest(name = "{0}, {1}, {2}")
    @CsvSource(value = {"1, ' ', content", "2, title2, ' '", "3, ' ', ' '"}, nullValues = {"null"})
    void write_Article_Blank(String articleId, String title, String content) throws Exception{

        mockMvc.perform(post("/articles")
                        .param("articleId", articleId)
                        .param("title", title)
                        .param("content", content))
                .andExpect(status().isBadRequest());
    }
}