package com.kakao.cafe.controller;

import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.vo.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Resource(name = "articleService")
    private ArticleService articleService;

    @GetMapping
    String posts(Model model) {
        model.addAttribute("articles", articleService.getArticleList());
        return "index";
    }

    @PostMapping("/question")
    String question(@Valid Article article) {
        articleService.insertArticle(article);
        logger.info("create Article -> Writer : {}, Title : {}", article.getWriter(), article.getTitle());
        return "redirect:/";
    }

    @GetMapping("/articles/{articleId}")
    String show(@PathVariable long articleId, Model model) {
        Article article = articleService.getArticleById(articleId);
        model.addAttribute("article", article);
        model.addAttribute("articleId", articleId);
        logger.info("get Article -> articleId : {}", articleId);
        return "qna/show";
    }

}
