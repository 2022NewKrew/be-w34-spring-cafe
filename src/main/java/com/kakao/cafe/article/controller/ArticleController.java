package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.ArticlePostRequest;
import com.kakao.cafe.article.service.ArticleService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/article")
    public String post(@Valid ArticlePostRequest request) {
        articleService.post(request.toEntity());
        return "redirect:/";
    }

    @GetMapping
    public String getAllArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "index";
    }

    @GetMapping("/article/{articleId}")
    public String getArticle(@PathVariable Long articleId, Model model) {
        model.addAttribute("article", articleService.getArticleById(articleId));
        return "article/show";
    }
}
