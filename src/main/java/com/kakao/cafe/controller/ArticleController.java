package com.kakao.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.service.ArticleService;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/questions")
    public String createArticle(ArticleDTO articleDTO) {
        articleService.save(articleDTO);
        return "redirect:/";
    }

    @GetMapping("/")
    public String listArticles(Model model) {
        model.addAttribute("articles", articleService.findAll().getArticles());
        return "index";
    }

    @GetMapping("/articles/{index}")
    public String showDetailedArticle(@PathVariable Integer index, Model model) {
        ArticleDTO articleDTO = articleService.findById(index);
        model.addAttribute("title", articleDTO.getTitle());
        model.addAttribute("content", articleDTO.getContent());
        return "qna/show";
    }
}

