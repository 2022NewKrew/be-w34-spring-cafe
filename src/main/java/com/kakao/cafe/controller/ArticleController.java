package com.kakao.cafe.controller;

import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.vo.Article;
import com.kakao.cafe.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String questionList(Model model) {
        List<Article> articleList = articleService.getArticles();
        model.addAttribute("articles", articleList);
        return "index";
    }


    @PostMapping
    public String createQuestion(Article article) {
        articleService.write(article);
        return "redirect:/articles";
    }

//    @GetMapping("/")

}
