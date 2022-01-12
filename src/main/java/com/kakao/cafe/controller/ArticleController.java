package com.kakao.cafe.controller;

import com.kakao.cafe.domain.dtos.ArticleSaveDto;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles/post")
    public String createArticlePage() {
        return "qna/form";
    }

    @GetMapping("/articles")
    public String articleListPage(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "qna/list";
    }

    @PostMapping("/articles")
    public String createArticle(@ModelAttribute ArticleSaveDto dto) {
        articleService.save(dto);
        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String articlePage(@PathVariable long id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "qna/show";
    }
}
