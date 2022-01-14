package com.kakao.cafe.module.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 게시물_작성() throws Exception {
        ArticlePostDto input = new ArticlePostDto("레인", "제목", "내용");

        mockMvc.perform(post("/articles")
                        .flashAttr("articlePostDto", input))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void 존재하지_않는_사용자로_게시물_작성() throws Exception {
        ArticlePostDto input = new ArticlePostDto("없는사용자", "제목", "내용");

        mockMvc.perform(post("/articles")
                        .flashAttr("articlePostDto", input))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @Test
    void 게시물_열람() throws Exception {
        mockMvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attribute("article", hasProperty("author", is("레인"))))
                .andExpect(view().name("article/show"));
    }

    @Test
    void 존재하지_않는_게시물_열람() throws Exception {
        mockMvc.perform(get("/articles/100"))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }
}