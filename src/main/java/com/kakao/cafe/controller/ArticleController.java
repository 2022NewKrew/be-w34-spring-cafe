package com.kakao.cafe.controller;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String getArticleList(Model model) {
        List<Article> articleList = articleService.getArticleList();
        model.addAttribute("articleList", articleList);
        return "index";
    }

    @PostMapping("/qna/create")
    public String createQuestion(Article article) {
        articleService.createQuestion(article);
        return "redirect:/";
    }
}
