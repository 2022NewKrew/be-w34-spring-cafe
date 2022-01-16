package com.kakao.cafe.controller;

import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.domain.article.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @GetMapping(value = "/")
    public String indexPage() {
        return "/index";
    }

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/article/form")
    public String createArticleForm() {
        return "/article/form";
    }

    @PostMapping(value = "/article/create")
    public String postCreateArticle(Article article) {
        logger.info("article:{}", article);
        articleService.getArticleRepository().getArticleList().add(article);
        return "redirect:/index";
    }

    @GetMapping(value = "/index")
    public String getArticleList(Model model) {
        logger.info("articleList:{}", articleService.getArticleRepository().getArticleList().getCopiedArticleList());
        model.addAttribute("articleListSize", articleService.getArticleRepository().getArticleList().size());
        model.addAttribute("articleList", articleService.getArticleRepository().getArticleList().getCopiedArticleList());
        return "/index";
    }

    @GetMapping(value = "/articles/{index}")
    public String getArticleShow(@PathVariable int index, Model model) {
        Article article = articleService.getArticleRepository().getArticleList().getArticleByIndex(index);
        model.addAttribute("article", article);
        return "/article/show";
    }
}
