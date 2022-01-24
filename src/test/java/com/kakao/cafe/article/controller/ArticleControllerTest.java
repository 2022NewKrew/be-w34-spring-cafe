package com.kakao.cafe.article.controller;

import static com.kakao.cafe.fixture.ArticleFixture.ARTICLE1;
import static com.kakao.cafe.fixture.ArticleFixture.ARTICLES;
import static com.kakao.cafe.fixture.ArticleFixture.SINGLE_ARTICLE1;
import static com.kakao.cafe.fixture.CommentFixture.COMMENTS;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.kakao.cafe.ControllerTest;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.article.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(ArticleController.class)
class ArticleControllerTest extends ControllerTest {

    @MockBean
    private ArticleService articleService;

    @MockBean
    private CommentService commentService;

    @DisplayName("게시글 등록")
    @Test
    void postArticle() throws Exception {
        loginUser();

        mockMvc.perform(
                post("/article")
                    .param("title", ARTICLE1.getTitle())
                    .param("body", ARTICLE1.getBody())
                    .session(session)
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));
    }

    @DisplayName("게시글 목록 조회")
    @Test
    void getAllArticles() throws Exception {
        given(articleService.getAllArticles()).willReturn(ARTICLES);

        mockMvc.perform(
                get("/article")
            )
            .andExpect(status().isOk())
            .andExpect(view().name("article/list"))
            .andExpect(model().attribute("articles", ARTICLES));
    }

    @DisplayName("게시글 상세 조회")
    @Test
    void getArticle() throws Exception {
        loginUser();
        given(articleService.getSingleArticle(anyLong())).willReturn(SINGLE_ARTICLE1);
        given(commentService.getAllComments(anyLong())).willReturn(COMMENTS);

        mockMvc.perform(
                get("/article/" + SINGLE_ARTICLE1.getArticleId())
                    .session(session)
            )
            .andExpect(status().isOk())
            .andExpect(view().name("article/show"))
            .andExpect(model().attribute("article", SINGLE_ARTICLE1))
            .andExpect(model().attribute("comments", COMMENTS));
    }

    @DisplayName("게시글 수정 페이지")
    @Test
    void updateForm() throws Exception {
        loginUser();
        given(articleService.getSingleArticle(anyLong())).willReturn(SINGLE_ARTICLE1);

        mockMvc.perform(
                get("/article/" + SINGLE_ARTICLE1.getArticleId() + "/update-form")
                    .session(session)
            )
            .andExpect(status().isOk())
            .andExpect(view().name("article/update-form"))
            .andExpect(model().attribute("article", SINGLE_ARTICLE1));
    }

    @DisplayName("게시글 수정")
    @Test
    void updateArticle() throws Exception {
        loginUser();

        mockMvc.perform(
                put("/article/" + SINGLE_ARTICLE1.getArticleId())
                    .session(session)
                    .param("title", "updated title")
                    .param("body", "updated body")
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/article/" + SINGLE_ARTICLE1.getArticleId()));
    }

    @DisplayName("게시글 삭제")
    @Test
    void deleteArticle() throws Exception {
        loginUser();

        mockMvc.perform(
                delete("/article/" + SINGLE_ARTICLE1.getArticleId())
                    .session(session)
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));
    }
}