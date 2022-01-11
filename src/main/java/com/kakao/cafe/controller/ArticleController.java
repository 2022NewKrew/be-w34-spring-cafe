package com.kakao.cafe.controller;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.ArticleRequest;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String getArticleList(Model model) {
        List<Article> articleList = articleService.getArticleList();
        model.addAttribute("articleList", articleList);
        return "index";
    }

    @PostMapping("/qna/create")
    public String createQuestion(ArticleRequest articleRequest) {
        articleService.createQuestion(articleRequest);
        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String getArticleInfo(@PathVariable("index") String index, Model model) {
        Article article = articleService.findById(index);
        model.addAttribute("article", article);
        return "qna/show";
    }
}
