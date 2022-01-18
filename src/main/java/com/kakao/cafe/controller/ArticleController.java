package com.kakao.cafe.controller;

import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.vo.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/article/create")
    public String questionForm(Article article) {
        articleService.addArticle(article);
        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String getDetails(@PathVariable int index, Model model) {
        Article article = articleService.getArticle(index);
        model.addAttribute("article", article);
        return "/qna/show";
    }

    @GetMapping("/")
    public String getArticles(Model model) {
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("questions/form")
    public String getQuestionForm() {
        return "/qna/form";
    }

}
