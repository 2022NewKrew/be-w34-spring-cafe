package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.ArticleDto;
import com.kakao.cafe.domain.article.ArticleRequest;
import com.kakao.cafe.exception.EntityNotFoundException;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public String publish(ArticleRequest articleRequest) {
        articleService.publishArticle(articleRequest);

        return "redirect:/";
    }

    @GetMapping("/{index}")
    public String getArticle(@PathVariable Long index, Model model) {
        try {
            ArticleDto article = articleService.findById(index);
            model.addAttribute("article", article);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }

        return "article/show";
    }
}
