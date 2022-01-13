package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class RootController {
    Logger logger = LoggerFactory.getLogger(RootController.class);
    private final ArticleService articleService;

    @Autowired
    public RootController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping
    String root(Model model){
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);

        return "/index";
    }
}
