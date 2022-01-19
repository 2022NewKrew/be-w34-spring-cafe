package com.kakao.cafe.article.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.article.dto.ArticleDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final Long id = 1l;
    private final String title = "제목";
    private final String content = "내용";

    @Test
    @DisplayName("게시물 정상 저장")
    @Order(1)
    void test1() throws Exception {
        // given
        String postContent = objectMapper.writeValueAsString(ArticleDto.builder()
                .title(title)
                .content(content)
                .build());

        // when
        MvcResult result = this.mockMvc.perform(
                        post("/article")
                                .content(postContent)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    @DisplayName("게시글 상세 검색")
    @Order(2)
    void test2() throws Exception {

        // when
        MvcResult result = this.mockMvc.perform(
                        get("/article/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        ArticleDto articleDto = (ArticleDto) result.getModelAndView().getModelMap().get("article");

        // then
        assertThat(articleDto.getId()).isEqualTo(id);
        assertThat(articleDto.getTitle()).isEqualTo(title);
        assertThat(articleDto.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("게시글 목록 검색")
    @Order(2)
    void test3() throws Exception {
        MvcResult result = this.mockMvc.perform(
                        get("/article")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        List<ArticleDto> articles = (List<ArticleDto>) result.getModelAndView().getModelMap().get("articles");
        assertThat(articles.size()).isEqualTo(1);
    }
}
