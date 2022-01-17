package com.kakao.cafe.controller.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.Contents;
import com.kakao.cafe.model.article.Title;
import com.kakao.cafe.model.article.Writer;
import com.kakao.cafe.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("ArticleController 테스트")
class ArticleControllerTest {
    private ArticleService articleService;
    private ArticleController articleController;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    private void before() {
        articleService = mock(ArticleService.class);
        articleController = new ArticleController(articleService);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(articleController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @DisplayName("GET /index 테스트")
    @Test
    public void getIndex() throws Exception {
        //give
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("pages"));
    }

    @DisplayName("GET /index/{page} 테스트")
    @Test
    public void getIndexByPage() throws Exception {
        int page = 1;
        int articlesPerPage = 5;
        when(articleService.getPartOfArticles(page, articlesPerPage))
                .thenReturn(getArticles(articlesPerPage));
        System.out.println(articleService.findArticleById(1));
        mockMvc.perform(get("/index/" + page))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("pages"));
    }

    @DisplayName("GET /articles 테스트")
    @Test
    public void postArticle() throws Exception {
        String content = objectMapper.writeValueAsString(new ArticleCreateDto("title", "writer", "contents"));

        mockMvc.perform(
                        post("/articles")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("GET /articles/{id} 테스트")
    @Test
    public void getArticleDetail() throws Exception {
        int id = 1;
        when(articleService.findArticleById(id))
                .thenReturn(
                        new Article(
                                1,
                                new Title("title"),
                                new Writer("writer"),
                                new Contents("contents")
                        )
                );

        mockMvc.perform(get("/articles/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("qna/show"))
                .andExpect(model().attributeExists("article"));
    }

    private List<Article> getArticles(int number) {
        List<Article> articles = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            articles.add(
                    new Article(
                            i,
                            new Title("title" + i),
                            new Writer("writer" + i),
                            new Contents("contents" + i)
                    )
            );
        }
        return articles;
    }
}
