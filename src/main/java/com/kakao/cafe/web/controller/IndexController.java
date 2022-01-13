package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    private final ArticleService articleService;

    public IndexController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String index(Model model) {
        logger.info("GET /: response index page");

        // articles 조회
        List<Article> articles = articleService.findArticles();
        model.addAttribute("articles", articles);
        return "index";
    }
}
