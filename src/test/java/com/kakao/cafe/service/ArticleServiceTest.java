package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.web.dto.ArticleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    public ArticleService articleService;

    @Mock
    ArticleRepository articleRepository;

    @BeforeEach
    void setup() {
        this.articleService = new ArticleService(articleRepository);
    }


    @Test
    void createArticle() {
        articleService.createArticle(new ArticleDTO("Mock 이란?", "Mock로 테스트 하는 법이 궁금합니다."));
    }

    @Test
    void getArticleByIndex() {
        when(articleRepository.findById(1)).thenReturn(Article.newInstance(1, "Mock 이란?", "Mock 으로 테스트 하는 법이 궁금합니다.", "unknown", "2022-01-19", 0));
        ArticleDTO article = articleService.getArticleByIndex(1);
        assertEquals(article.getId(), 1);
        assertEquals(article.getTitle(), "Mock 이란?");
        verify(articleRepository, times(1)).findById(1);
    }

    @Test
    void getArticleList() {
    }

    @Test
    void getArticleListSize() {
        List<Article> articleList = new ArrayList<>();
        articleList.add(Article.newInstance(1, "Mock 이란?", "Mock 으로 테스트 하는 법이 궁금합니다.", "unknown", "2022-01-18", 0));
        articleList.add(Article.newInstance(2, "Spring 이란?", "Spring이 궁금합니다.", "unknown", "2022-01-19", 0));
        when(articleRepository.getArticleList()).thenReturn(articleList);
        assertEquals(articleService.getArticleListSize(), 2);
    }
}