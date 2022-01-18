package com.kakao.cafe.controller.article;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.controller.article.dto.ArticleCreateDto;
import com.kakao.cafe.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@DisplayName("ArticleSaveController 테스트")
class ArticleSaveControllerTest {

    private ArticleService articleService;
    private ArticleSaveController articleSaveController;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    private void before() {
        articleService = mock(ArticleService.class);
        articleSaveController = new ArticleSaveController(articleService);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(articleSaveController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @DisplayName("POST /articles 테스트")
    @Test
    public void postArticle() throws Exception {
        String content = objectMapper.writeValueAsString(
                new ArticleCreateDto("title", "writer", "contents"));

        mockMvc.perform(
                        post("/articles")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
