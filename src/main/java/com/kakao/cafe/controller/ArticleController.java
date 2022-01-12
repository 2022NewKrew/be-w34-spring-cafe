package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String findArticleList(Model model) {
        List<Article> articles = articleService.findArticleList();
        model.addAttribute("articles" , articles);
        return "index";
    }

    @PostMapping("/questions")
    public String create(ArticleDto articleDto) {
        articleService.save(articleDto);
        return "redirect:/";
    }
    @GetMapping("/articles/{id}")
    public String findUser(@PathVariable("id") Long articleId, Model model) {
        Article article = articleService.findArticle(articleId);
        model.addAttribute("article" , article);
        return "qna/show";
    }

}
