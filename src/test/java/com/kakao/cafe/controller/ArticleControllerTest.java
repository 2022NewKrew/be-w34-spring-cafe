package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Reply;
import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.dto.article.ArticleDetailDto;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ReplyDto;
import com.kakao.cafe.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = ArticleController.class,
        properties = "spring.cloud.vault.fail-fast=false")
class ArticleControllerTest {

    private static final long FIRST_ARTICLE_ID = 1L;
    private static final long FIRST_USER_ID = 10L;
    private static final long SECOND_USER_ID = 20L;
    private static final String FIRST_AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final boolean NOT_DELETED = false;
    private static final Article FIRST_USER_ARTICLE =
            new Article(FIRST_ARTICLE_ID, FIRST_USER_ID, FIRST_AUTHOR, TITLE, DESCRIPTION, NOT_DELETED);

    private static final long FIRST_REPLY_ID = 100L;
    private static final Reply FIRST_USER_REPLY =
            new Reply(FIRST_REPLY_ID, FIRST_ARTICLE_ID, FIRST_USER_ID, FIRST_AUTHOR, DESCRIPTION, NOT_DELETED);
    private static final List<ReplyDto> REPLIES = new ArrayList<>(List.of(new ReplyDto(FIRST_USER_REPLY)));

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("[POST] /articles - 새 게시글을 작성할 수 있다")
    void publish() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("auth", new Auth(FIRST_USER_ID));

        mockMvc.perform(post("/articles")
                        .param("title", TITLE)
                        .param("description", DESCRIPTION)
                        .session(mockSession)
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("[POST] /articles/{articleId}/reply - 새 댓글을 작성할 수 있다")
    void createReply() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("auth", new Auth(FIRST_USER_ID));

        mockMvc.perform(post("/articles/" + FIRST_ARTICLE_ID + "/reply")
                        .param("description", DESCRIPTION)
                        .session(mockSession)
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/articles/" + FIRST_ARTICLE_ID));
    }

    @Test
    @DisplayName("[POST] /articles - 로그인하지 않고 게시글을 작성하는 경우 실패하며 로그인 페이지로 이동한다")
    void failToPublishWithNoSession() throws Exception {
        mockMvc.perform(post("/articles")
                        .param("title", TITLE)
                        .param("description", DESCRIPTION)
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user/login"));
    }


    @Test
    @DisplayName("[GET] /articles/{articleId} - 게시글의 상세 내용을 조회할 수 있다")
    void getArticle() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("auth", new Auth(FIRST_USER_ID));
        Mockito.when(articleService.findArticleDetailById(FIRST_ARTICLE_ID))
                        .thenReturn(new ArticleDetailDto(FIRST_USER_ARTICLE, REPLIES));

        mockMvc.perform(get("/articles/" + FIRST_ARTICLE_ID)
                        .session(mockSession)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("article/show"))
                .andExpect(model().attributeExists("article"));
    }

    @Test
    @DisplayName("[GET] /articles/{articleId} - 로그인하지 않고 게시글의 상세조회를 할 수 없다")
    void failToGetArticleWithNoSession() throws Exception {
        Mockito.when(articleService.findArticleById(FIRST_ARTICLE_ID))
                .thenReturn(new ArticleDto(FIRST_USER_ARTICLE));

        mockMvc.perform(get("/articles/" + FIRST_ARTICLE_ID))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user/login"));
    }

    @Test
    @DisplayName("[PUT] /articles/{articleId} - 게시글의 제목과 내용을 수정할 수 있다")
    void update() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("auth", new Auth(FIRST_USER_ID));
        Mockito.when(articleService.findArticleById(FIRST_ARTICLE_ID))
                .thenReturn(new ArticleDto(FIRST_USER_ARTICLE));

        mockMvc.perform(put("/articles/" + FIRST_ARTICLE_ID)
                        .session(mockSession)
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/articles/" + FIRST_ARTICLE_ID));
    }

    @Test
    @DisplayName("[PUT] /articles/{articleId} - 다른 유저의 게시글은 수정할 수 없다")
    void failToUpdateWithInvalidSession() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("auth", new Auth(SECOND_USER_ID));
        Mockito.when(articleService.findArticleById(FIRST_ARTICLE_ID))
                .thenReturn(new ArticleDto(FIRST_USER_ARTICLE));

        mockMvc.perform(put("/articles/" + FIRST_ARTICLE_ID)
                        .session(mockSession)
                )
                .andExpect(status().isUnauthorized())
                .andExpect(view().name("error"));
    }

    @Test
    @DisplayName("[DELETE] /articles/{articleId} - 게시글을 삭제할 수 있다")
    void deleteArticle() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("auth", new Auth(FIRST_USER_ID));
        Mockito.when(articleService.findArticleById(FIRST_ARTICLE_ID))
                .thenReturn(new ArticleDto(FIRST_USER_ARTICLE));

        mockMvc.perform(delete("/articles/" + FIRST_ARTICLE_ID)
                        .session(mockSession)
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("[DELETE] /articles/{articleId} - 다른 유저의 게시글은 삭제할 수 없다")
    void failToDeleteWithInvalidSession() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("auth", new Auth(SECOND_USER_ID));
        Mockito.when(articleService.findArticleById(FIRST_ARTICLE_ID))
                .thenReturn(new ArticleDto(FIRST_USER_ARTICLE));

        mockMvc.perform(delete("/articles/" + FIRST_ARTICLE_ID)
                        .session(mockSession)
                )
                .andExpect(status().isUnauthorized())
                .andExpect(view().name("error"));
    }

    @Test
    @DisplayName("[DELETE] /articles/{articleId}/reply/{replyId} - 댓글을 삭제할 수 있다")
    void deleteReply() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("auth", new Auth(FIRST_USER_ID));
        Mockito.when(articleService.findReplyById(FIRST_REPLY_ID))
                .thenReturn(new ReplyDto(FIRST_USER_REPLY));

        mockMvc.perform(delete("/articles/" + FIRST_ARTICLE_ID + "/reply/" + FIRST_REPLY_ID)
                        .session(mockSession)
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/articles/" + FIRST_ARTICLE_ID));
    }

}
