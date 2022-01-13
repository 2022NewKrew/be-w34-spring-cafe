package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.dto.ArticleForm;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("")
    public String registerArticle(@Valid ArticleForm articleFrom){
        logger.info("POST /articles");
        Article article = Article.of(articleFrom);
        articleService.register(article);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showArticle(@PathVariable Long id, Model model){
        logger.info("GET /{id}");
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article/show";
    }
}
