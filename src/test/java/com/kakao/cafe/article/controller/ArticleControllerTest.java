package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.Contents;
import com.kakao.cafe.article.domain.Title;
import com.kakao.cafe.article.dto.ArticleFormDto;
import com.kakao.cafe.article.dto.ArticleMapper;
import com.kakao.cafe.article.service.ArticlePostService;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.user.domain.UserId;
import com.kakao.cafe.util.ErrorCode;
import com.kakao.cafe.util.ExceptionController;
import com.kakao.cafe.util.exception.ArticleException;
import com.kakao.cafe.util.exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {

    @MockBean
    private ArticleService articleService;
    @MockBean
    private ArticlePostService articlePostService;
    @MockBean
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleController articleController;
    @Autowired
    private MockMvc mockMvc;
    private ArticleFormDto articleFormDto;
    private Article article;
    private Article articleContainId;

    @BeforeEach
    void setUp() {
        articleFormDto = new ArticleFormDto("terwt", "에러 발생", "존재하지 않는 아이디로 게시물 작성");
        article = new Article(
                new UserId(articleFormDto.getWriter()),
                new Date(),
                new Title(articleFormDto.getTitle()),
                new Contents(articleFormDto.getContents())
        );
        articleContainId = new Article(1L, article);

        mockMvc = MockMvcBuilders.standaloneSetup(articleController)
                .setControllerAdvice(ExceptionController.class)
                .build();
    }

    @Test
    @DisplayName("Article Form 정상")
    void validArticleForm() throws Exception {
        mockMvc.perform(get("/question/form")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Article Title이 잘못된 경우 에러가 발생한다.")
    void invalidArticleTitle() throws Exception {
        ArticleFormDto articleFormDto = new ArticleFormDto("test1", "", "빈 타이틀은 에러가 납니다...");

        when(articleMapper.toArticle(articleFormDto))
                .thenThrow(new ArticleException(ErrorCode.INVALID_ARTICLE_TITLE));

        mockMvc.perform(post("/question/create")
                .param("writer", articleFormDto.getWriter())
                .param("title", articleFormDto.getTitle())
                .param("contents", articleFormDto.getContents())
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Article Contents가 잘못된 경우 에러가 발생한다.")
    void invalidArticleContents() throws Exception {
        ArticleFormDto articleFormDto = new ArticleFormDto("test1", "빈 컨텐츠도 에러가 납니다..", "");

        when(articleMapper.toArticle(articleFormDto))
                .thenThrow(new ArticleException(ErrorCode.INVALID_ARTICLE_CONTENTS));

        mockMvc.perform(post("/question/create")
                .param("writer", articleFormDto.getWriter())
                .param("title", articleFormDto.getTitle())
                .param("contents", articleFormDto.getContents())
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Article WriterId가 존재하지 않는 경우 에러가 발생한다.")
    void invalidArticleWriterId() throws Exception {
        Article articleExistUser = new Article(new UserId("t1234"), new Date(), new Title("존재하는 아이디"), new Contents("게시물 작성 가능"));

        when(articleMapper.toArticle(articleFormDto))
                .thenReturn(articleExistUser);

        when(articlePostService.postArticle(articleExistUser))
                .thenThrow(new UserException(ErrorCode.USER_NOT_FOUND));

        mockMvc.perform(post("/question/create")
                .param("writer", articleFormDto.getWriter())
                .param("title", articleFormDto.getTitle())
                .param("contents", articleFormDto.getContents())
        ).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Article 정상 등록")
    void postValidArticle() throws Exception {
        when(articlePostService.postArticle(article))
                .thenReturn(articleContainId);

        mockMvc.perform(post("/question/create")
                .param("writer", "terwt")
                .param("title", "정상 등록")
                .param("contents", "존재하는 아이디입니다.")
        ).andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("ArticleId가 존재하지 않는 경우 에러가 발생한다.")
    void invalidArticleId() throws Exception {
        when(articleService.findByArticleId(0L))
                .thenThrow(new UserException(ErrorCode.ARTICLE_NOT_FOUND));

        mockMvc.perform(get("/articles/0")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("ArticleId가 존재하는 경우 정상적인 결과가 출력된다.")
    void validArticleId() throws Exception {
        when(articleService.findByArticleId(1L))
                .thenReturn(articleContainId);

        mockMvc.perform(get("/articles/1")).andExpect(status().isOk());
    }
}
