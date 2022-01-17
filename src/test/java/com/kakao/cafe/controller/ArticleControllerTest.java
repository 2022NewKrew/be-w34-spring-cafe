package com.kakao.cafe.controller;

import com.kakao.cafe.config.Mapper;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Text;
import com.kakao.cafe.domain.article.Time;
import com.kakao.cafe.domain.article.Title;
import com.kakao.cafe.domain.member.*;
import com.kakao.cafe.dto.ArticleListDto;
import com.kakao.cafe.dto.CommentDto;
import com.kakao.cafe.dto.InquireArticleDto;
import com.kakao.cafe.service.article.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArticleController.class)
@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private Mapper mapper;
    @MockBean
    private ArticleService articleService;

    @Test
    @DisplayName("전체 게시글 조회")
    void inquireAllArticlesTest() throws Exception {
        // given
        Time time = Time.now();
        Article article = new Article(new Title("title"),
                new Text("text"),
                time);
        article.setAuthor(new Member(new UserId("rubam"), new Name("david"), new Password("Ab12345!"), new Email("wooky9633@naver.com"), 1L));
        ArticleListDto articleListDto = new ArticleListDto(1L, 1L, "title", "rubam", time.toString());

        List<ArticleListDto> result = new ArrayList<>();
        List<Article> articles = new ArrayList<>();
        articles.add(article);
        result.add(articleListDto);

        given(articleService.inquireAllArticles()).willReturn(articles);
        given(mapper.convertToList(article)).willReturn(articleListDto);

        // then
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("articles", result))
                .andExpect(view().name("index"));
    }

    @Test
    @DisplayName("단일 게시글 상세 조회")
    void inquireOneArticleTest() throws Exception {
        // given
        Time time = Time.now();
        Article article = new Article(new Title("title"),
                new Text("text"),
                time);
        article.setAuthor(new Member(new UserId("rubam"), new Name("david"), new Password("Ab12345!"), new Email("wooky9633@naver.com"), 1L));
        List<CommentDto> comments = new ArrayList<>();
        InquireArticleDto articleDto = new InquireArticleDto(1L, 1L, "title", "text", "rubam", time.toString(), comments);

        given(articleService.inquireOneArticle(1L)).willReturn(article);
        given(mapper.map(article)).willReturn(articleDto);

        // then
        mockMvc.perform(get("/questions/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("article", articleDto))
                .andExpect(view().name("qna/show"));
    }

    @Test
    @DisplayName("게시글 작성 시 게시글 목록으로 리다이렉션 테스트")
    void postArticleTest() throws Exception {
        // then
        mockMvc.perform(post("/questions")
                        .content("title=title&contents=text&writer=rubam")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
