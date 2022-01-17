package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.ArticlePostRequest;
import com.kakao.cafe.article.service.ArticleService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public String post(@Valid ArticlePostRequest request) {
        articleService.post(request.toEntity());
        return "redirect:/";
    }

    @GetMapping
    public String getAllArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "article/list";
    }

    @GetMapping("/{articleId}")
    public String getArticle(@PathVariable Long articleId, Model model) {
        model.addAttribute("article", articleService.getArticleById(articleId));
        return "article/show";
    }
}
