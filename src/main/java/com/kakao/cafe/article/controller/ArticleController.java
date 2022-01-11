package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.request.ArticleCreateRequest;
import com.kakao.cafe.article.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final Logger logger;

    private ArticleController(ArticleService articleService) {
        this.articleService = articleService;
        this.logger = LoggerFactory.getLogger(ArticleController.class);
    }

    @GetMapping("/article/write")
    public String getArticleCreatePage() {
        return "article/write";
    }

    @GetMapping("article/show")
    public String getArticleShowPage() {
        return "article/show";
    }
}
