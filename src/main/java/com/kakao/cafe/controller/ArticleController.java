package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleForm;
import com.kakao.cafe.entiry.Article;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("")
    public String registerArticle(ArticleForm articleFrom){
        logger.info("POST /articles");
        Article article = Article.of(articleFrom);
        articleService.register(article);
        return "redirect:/";
    }
}
