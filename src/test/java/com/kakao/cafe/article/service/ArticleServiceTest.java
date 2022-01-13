package com.kakao.cafe.article.service;

import com.kakao.cafe.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleService articleService;

}
