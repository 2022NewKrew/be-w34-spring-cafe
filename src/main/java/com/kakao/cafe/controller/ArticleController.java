package com.kakao.cafe.controller;

import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final ArticleService articleService;

    @GetMapping
    public String findArticles(Model model) {
        model.addAttribute("articles", articleService.findAll());
        logger.info(articleService.findAll().get(0).toString());
        return "index";
    }

    @GetMapping("{id}")
    public String findArticleOne(@PathVariable int id, Model model) {
        logger.info(articleService.findById(id).toString());
        model.addAttribute("article", articleService.findById(id));
        return "/qna/show";
    }

    @PostMapping
    public String createArticle(@ModelAttribute ArticleDto articleDto) {
        int id = articleService.create(articleDto);
        logger.info(id + " success");
        return "redirect:/";
    }

}
