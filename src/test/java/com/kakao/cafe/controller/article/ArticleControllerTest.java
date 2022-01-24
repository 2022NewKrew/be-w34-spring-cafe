package com.kakao.cafe.controller.article;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.controller.Constant;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.service.article.dto.ArticleCreateDto;
import com.kakao.cafe.service.article.dto.ArticleDto;
import com.kakao.cafe.service.article.dto.ArticleUpdateDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@DisplayName("ArticleController 테스트")
class ArticleControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ArticleService articleService;
    private ArticleController articleController;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    private void before() {
        articleService = mock(ArticleService.class);
        articleController = new ArticleController(articleService);
        session = new MockHttpSession();

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(articleController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @DisplayName("GET /index/{page} 테스트")
    @Test
    public void getIndexByPage() throws Exception {
        int page = 4;
        int articlesPerPage = Constant.MAX_ARTICLES;
        int pageLimit = 5;
        when(articleService.getPartOfArticles(page, articlesPerPage))
                .thenReturn(getArticles(articlesPerPage));
        when(articleService.getPages(articlesPerPage, pageLimit, page))
                .thenReturn(List.of(1, 2, 3, 4, 5));
        when(articleService.getLastPageNumber(articlesPerPage))
                .thenReturn(5);
        mockMvc.perform(get("/index/" + page))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("pages"))
                .andExpect(model().attributeExists("previousPage"))
                .andExpect(model().attributeExists("nextPage"));
    }

    @DisplayName("POST /articles 테스트")
    @Test
    public void postArticle() throws Exception {
        String content = objectMapper.writeValueAsString(
                new ArticleCreateDto("title", "userId", "contents"));

        mockMvc.perform(
                        post("/articles")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("GET /articles 테스트")
    @Test
    public void getArticleDetail() throws Exception {
        int id = 1;
        when(articleService.findArticleById(id))
                .thenReturn(
                        new ArticleDto(
                                1,
                                "title",
                                "userId",
                                "contents",
                                LocalDateTime.now()));

        mockMvc.perform(get("/articles?id=" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("qna/show"))
                .andExpect(model().attributeExists("article"));
    }

    @DisplayName("GET /articles/update 테스트")
    @Test
    public void showArticleUpdateForm() throws Exception {
        int id = 1;
        session.setAttribute("loginUserId", "userId");
        when(articleService.findArticleById(id))
                .thenReturn(
                        new ArticleDto(
                                1,
                                "title",
                                "userId",
                                "contents",
                                LocalDateTime.now()));

        mockMvc.perform(get("/articles/update?id=" + id)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("qna/updateForm"))
                .andExpect(model().attributeExists("article"));
    }

    @DisplayName("PUT /articles/update")
    @Test
    public void articleUpdate() throws Exception {
        ArticleUpdateDto articleUpdateDto = new ArticleUpdateDto("title", "userId", "contents");
        int id = 1;
        String content = objectMapper.writeValueAsString(articleUpdateDto);
        String query = "?id=" + id;
        System.out.println(content);
        mockMvc.perform(put("/articles/update" + query)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/articles" + query));
    }

    @DisplayName("DELETE /articles/delete")
    @Test
    public void deleteArticle() throws Exception {
        ArticleDto articleDto = new ArticleDto(
                1,
                "title",
                "userId",
                "contents",
                LocalDateTime.now()
        );
        String query = "?id=" + 1;
        session.setAttribute("loginUserId", "userId");

        mockMvc.perform(delete("/articles/delete" + query)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    private List<ArticleDto> getArticles(int number) {
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            articleDtos.add(
                    new ArticleDto(
                            i,
                            "title" + i,
                            "userId" + i,
                            "contents" + i,
                            LocalDateTime.now()));
        }
        return articleDtos;
    }
}
