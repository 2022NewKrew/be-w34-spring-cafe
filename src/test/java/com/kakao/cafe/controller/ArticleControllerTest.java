package com.kakao.cafe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.article.controller.ArticleController;
import com.kakao.cafe.article.dto.ArticlePostDto;
import com.kakao.cafe.article.dto.ArticleRequest;
import com.kakao.cafe.article.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {ArticleController.class})
@DisplayName("Article 컨트롤러 테스트")
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ArticleService articleService;

    @ParameterizedTest
    @ValueSource(longs = {1, 2})
    @DisplayName("[GET] /article/show/{id} 테스트")
    public void getOneArticleTest(Long id) throws Exception {
        ArticlePostDto articlePostDto =
                new ArticlePostDto(id, "author", "title", "contents", "2022-01-17");

        when(articleService.getArticlePostDtoById(id)).thenReturn(articlePostDto);

        mockMvc.perform(get("/article/show/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("/qna/show"));
    }

    @ParameterizedTest
    @MethodSource("getArticles")
    @DisplayName("[POST] /article/question 테스트")
    public void postArticleTest(ArticleRequest articleRequest) throws Exception {
        String content = objectMapper.writeValueAsString(articleRequest);

        mockMvc.perform(post("/article/question")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    private static Stream<ArticleRequest> getArticles(){
        return Stream.of(
                new ArticleRequest("author", "title", "contents"),
                new ArticleRequest("author2", "title2", "contents2"),
                new ArticleRequest("author3", "title3", "contents3")
        );
    }
}