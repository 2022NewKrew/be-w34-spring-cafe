package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ArticleController.class)
class ArticleControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    private User user;
    private Article article;

    @BeforeEach
    void setUp() {
        user = new User("test123", "test1234", "One Test", "test1@test.com");
        user.setId(1L);

        article = new Article();
        article.setTitle("test1");
        article.setContent("test123");
        article.setAuthor(user);
        article.setId(1L);
    }

    @Test
    void update() throws Exception {
        when(articleService.fetch(article.getId())).thenReturn(article);

        mockMvc.perform(put("/articles/" + article.getId())
                        .sessionAttr("user", user)
                        .requestAttr("title", "test2")
                        .requestAttr("contents", "test456"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void delete() throws Exception {
        when(articleService.fetch(article.getId())).thenReturn(article);

        mockMvc.perform(MockMvcRequestBuilders.delete("/articles/" + article.getId())
                        .sessionAttr("user", user))
                .andExpect(status().is3xxRedirection());
    }
}