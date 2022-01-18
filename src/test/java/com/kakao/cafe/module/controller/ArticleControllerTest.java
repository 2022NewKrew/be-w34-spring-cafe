package com.kakao.cafe.module.controller;

import com.kakao.cafe.module.model.dto.UserDtos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    MockHttpSession session;

    @BeforeEach
    public void init() {
        session = new MockHttpSession();
        session.setAttribute("sessionUser", new UserDtos.UserDto(1L, "rain", "레인", "rain@rain.com"));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void clear() {
        session.clearAttributes();
    }

    @Test
    void 게시물_작성() throws Exception {
        ArticlePostDto input = new ArticlePostDto("제목", "내용");

        mockMvc.perform(post("/articles")
                        .flashAttr("articlePostDto", input)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void 로그인_없이_게시물_작성() throws Exception {
        ArticlePostDto input = new ArticlePostDto("제목", "내용");

        mockMvc.perform(post("/articles")
                        .flashAttr("articlePostDto", input))
                .andExpect(status().isUnauthorized())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("user/login"));
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