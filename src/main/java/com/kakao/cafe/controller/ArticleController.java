package com.kakao.cafe.controller;

import com.kakao.cafe.domain.dtos.ArticleSaveDto;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/post")
    public String createArticlePage() {
        return "qna/form";
    }

    @GetMapping("")
    public String articleListPage(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "qna/list";
    }

    @PostMapping("")
    public String createArticle(@ModelAttribute ArticleSaveDto dto) {
        articleService.save(dto);
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String articlePage(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "qna/show";
    }
}
