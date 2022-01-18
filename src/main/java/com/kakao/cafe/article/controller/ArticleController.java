package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.ArticleRegistrationDto;
import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class ArticleController {
    private final ArticleService service;

    @Autowired
    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        List<Article> articles = service.fetchAll();
        model.addAllAttributes(Map.of("articles", articles));
        model.addAttribute("articleCount", articles.size());
        return "index";
    }

    @PostMapping(value = "articles")
    public String register(ArticleRegistrationDto dto) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        service.create(article);
        return "redirect:/";
    }

    @GetMapping(value = "articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        Article article = service.fetch(id);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("content", article.getContent());
        return "post/show";
    }
}
