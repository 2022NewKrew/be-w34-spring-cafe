package com.kakao.cafe.article.service;

import com.kakao.cafe.article.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleService articleService;

    @Test
    @DisplayName("게시글 저장 및 조회 확인")
    void testArticleSaveAndFind() throws Exception {
        // given

        // when

        // then

    }
}
