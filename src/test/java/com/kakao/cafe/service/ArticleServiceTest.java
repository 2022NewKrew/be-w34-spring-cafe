package com.kakao.cafe.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void articleResponseDTOFromArticle() {
    }

    @Test
    void createArticle() {
    }

    @Test
    void getArticleListSize() {
        // assertEquals(articleService.getArticleListSize(),1);
    }

    @Test
    void getArticleList() {
    }

    @Test
    void getArticleByIndex() {
    }
}