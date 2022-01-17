package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.ArticleService;
import com.kakao.cafe.domain.article.dto.ArticleCreateRequestDto;
import com.kakao.cafe.domain.article.dto.ArticleResponseDto;
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

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        ArticleResponseDto article = articleService.retrieveArticle(id);
        model.addAttribute("article", article);
        return "article/show";
    }

    @PostMapping("/articles")
    public String createArticle(ArticleCreateRequestDto requestDto) {
        articleService.createArticle(requestDto);
        return "redirect:/";
    }

}
