package com.kakao.cafe.landing.controller;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class LandingController {

    private ArticleService articleService;

    @GetMapping
    public String getLandingPage(Model model){
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);

        return "/index";
    }
}
