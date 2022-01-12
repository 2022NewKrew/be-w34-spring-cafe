package com.kakao.cafe.web.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.web.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleApiController {
    private final ArticleService articleService = new ArticleService();

    @GetMapping("/")
    String getArticles(Model model) {
        List<Article> articleList = articleService.getArticleList();
        model.addAttribute("articles", articleList);
        return "index";
    }

    @GetMapping("/articles/form")
    String getForm() {
        return "articles/form";
    }

    @GetMapping("/articles/{id}")
    String findById(@PathVariable int id, Model model) {
        Article article = articleService.getByArticleId(id);
        model.addAttribute("article", article);
        return "articles/show";
    }

    @PostMapping("/articles")
    String createArticle(Article article) {
        articleService.addArticle(article);
        return "redirect:/";
    }
}
