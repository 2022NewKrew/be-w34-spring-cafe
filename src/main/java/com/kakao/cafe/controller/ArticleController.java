package com.kakao.cafe.controller;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public String articleListView(Model model) {
        List<Article> articleList = articleService.getArticleList();
        model.addAttribute("articles", articleList);
        return "index";
    }

    @GetMapping("/articles/{index}")
    public String articleView(@PathVariable int index, Model model) {
        Article article = articleService.filterArticleByIndex(index);
        model.addAttribute("article", article);
        return "qna/show";
    }

    @GetMapping("/articles")
    public String writeArticleView() {
        return "qna/form";
    }

    @PostMapping("/articles")
    public String writeArticle(Article article) {
        articleService.writeArticle(article);
        return "redirect:";
    }
}
