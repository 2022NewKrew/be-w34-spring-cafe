package com.kakao.cafe.controller;

import com.kakao.cafe.service.ArticleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final ArticleServiceImpl articleService;

    public MainController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("articles", articleService.getArticleList());
        return "/index";
    }
}
