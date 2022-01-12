package com.kakao.cafe.controller;

import com.kakao.cafe.service.ArticleService;
import domain.article.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value="/post/form.html")
    public String createArticleForm(){
        return "/post/form";
    }


    @PostMapping(value="/article/create")
    public String postCreateArticle(Article article){
        articleService.getArticleRepository().getArticleList().add(article);
        return "redirect:/index.html";
    }
}
