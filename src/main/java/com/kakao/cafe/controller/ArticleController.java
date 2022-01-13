package com.kakao.cafe.controller;

import com.kakao.cafe.dto.article.ArticleCreateDTO;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @PostMapping("/articles/create")
    public String create(@ModelAttribute @Validated ArticleCreateDTO articleCreateDTO) {
        articleService.create(articleCreateDTO);
        return "redirect:/";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "index";
    }

    @GetMapping("/articles/{index}")
    public String read(@PathVariable("index") Long articleId, Model model) {
        model.addAttribute("article", articleService.getArticle(articleId));
        return "qna/show";
    }
}