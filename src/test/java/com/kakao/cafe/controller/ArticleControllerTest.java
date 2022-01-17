package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    private static final long FIRST_ARTICLE_ID = 1L;
    private static final long SECOND_ARTICLE_ID = 2L;
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final Article mockArticle = new Article(FIRST_ARTICLE_ID, TITLE, DESCRIPTION);

    // TODO - 멤버 변수 author, reply, viewCount 추가 구현 & 테스트 예정
    // TODO - 로그인 여부에 따른 글쓰기 테스트 추가 예정

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("[POST] /articles - 새 게시글을 작성할 수 있다")
    void publish() throws Exception {
        mockMvc.perform(post("/articles")
                        .param("title", TITLE)
                        .param("description", DESCRIPTION)
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("[GET] /articles/{articleId} - 게시글의 상세 내용을 조회할 수 있다")
    void getArticle() throws Exception {
        Mockito.when(articleService.findById(FIRST_ARTICLE_ID))
                        .thenReturn(new ArticleDto(mockArticle));

        mockMvc.perform(get("/articles/" + FIRST_ARTICLE_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("article/show"))
                .andExpect(model().attributeExists("article"));
    }
}
