package com.kakao.cafe.controller;

import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final ArticleService articleService;

    @GetMapping
    public String findArticles(Model model) {
        List<ArticleDto> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        logger.info("GET /articles: {}", articles);
        return "index";
    }

    @GetMapping("{id}")
    public String findArticleOne(@PathVariable int id, Model model) {
        ArticleDto articles = articleService.findById(id);
        model.addAttribute("article", articles);
        logger.info("GET /articles/{}: {}", articles.getId(), articles);
        return "/qna/show";
    }

    @PostMapping
    public String createArticle(@ModelAttribute ArticleDto articleDto) {
        int id = articleService.create(articleDto);
        logger.info("POST /articles: {}", id);
        return "redirect:/";
    }

}
