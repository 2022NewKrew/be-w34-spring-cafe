package com.kakao.cafe.controller;

import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final ArticleService articleService;

    @Autowired
    private ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String findArticles(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "index";
    }

    @GetMapping("articles/{id}")
    public String findUserOne(@PathVariable int id, Model model) {
        logger.info(articleService.findById(id).toString());
        model.addAttribute("article", articleService.findById(id));
        return "/qna/show";
    }

    @PostMapping("articles")
    public String createArticle(@ModelAttribute ArticleDto articleDto) {
        int id = articleService.create(articleDto);
        logger.info(id + " success");
        return "redirect:/";
    }

}
