package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class ArticleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ArticleRepository articleRepository;

    Long articleId;

    @BeforeEach
    void fixture() {
        articleId = articleRepository.save(new Article("글쓴이", "글제목", "글내용"));
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
        mockMvc.perform(get("/articles/" + articleId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("article"))
                .andExpect(view().name("article/show"));
    }

    @DisplayName("게시글 생성 테스트")
    @Test
    void createArticleTest() throws Exception {
        mockMvc.perform(post("/articles")
                        .param("author", "글쓴이")
                        .param("title", "글제목")
                        .param("content", "글내용"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
