package com.kakao.cafe.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    void setup() throws Exception {
        mockMvc.perform(post("/users")
                .param("userName", "articleTestBaseId")
                .param("password", "articleTestBasePW")
                .param("name", "articleTestBaseName")
                .param("email", "articleTestBase@email.com"));
    }

    @Test
    void requestArticleList_Invoked_ReturnsCorrectModelAndView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("articles"))
                .andExpect(view().name("articles/list"));
    }

    @Test
    void requestArticleRegister_InvokedWithValidParameters_RedirectsCorrectly() throws Exception {
        mockMvc.perform(post("/articles")
                        .param("userName", "articleTestBaseId")
                        .param("title", "creationTestTitle")
                        .param("content", "creationTestContent"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void requestArticleRegister_InvokedWithoutSomeParameters_Status400() throws Exception {
        mockMvc.perform(post("/articles")
                        .param("userName", "articleTestBaseId")
                        .param("title", "invalidParameterTestTitle"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error/invalidinput"));

        mockMvc.perform(post("/articles")
                        .param("userName", "articleTestBaseId")
                        .param("content", "creationTestContent"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error/invalidinput"));

        mockMvc.perform(post("/articles")
                        .param("title", "creationTestTitle")
                        .param("content", "creationTestContent"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error/invalidinput"));
    }

    @Test
    void requestArticleRegister_InvokedWithNotExistingUserId_Status400() throws Exception {
        mockMvc.perform(post("/articles")
                        .param("userName", "noSuchUserTestId")
                        .param("title", "noSuchUserTestTitle")
                        .param("content", "noSuchUserTestContent"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/nosuchuser"));
    }

    @Disabled
    @Test
    void requestArticleDetail_InvokedWithValidParameter_ReturnsCorrectModelAndView() throws Exception {
        mockMvc.perform(post("/articles")
                .param("userName", "articleTestBaseId")
                .param("title", "DetailTestTitle")
                .param("content", "DetailTestContent"));

        mockMvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("article"))
                .andExpect(view().name("articles/detail"));
    }

    @Disabled
    @Test
    void requestArticleDetail_InvokedWithNotExistingArticleId_Status404() throws Exception {
        mockMvc.perform(get("/articles/" + Integer.MAX_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/nosucharticle"));
    }
}
