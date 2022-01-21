package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/articles")
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;
    private final UserService userService;

    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping()
    public String postCreateArticle(String title, String writerUsername, String contents) {
        final User writer = userService.getUserByUsername(writerUsername);
        final Article article = articleService.createArticle(title, writer, contents);
        LOGGER.info("POST request on createArticle -> {}", article);
        return "redirect:articles";
    }

    @GetMapping()
    public String getArticleList(Model model) {
        final List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "article/list";
    }

    @GetMapping("/{aid}")
    public String getArticleDetail(@PathVariable("aid") Integer aid, Model model) {
        final Article article = articleService.getArticleById(aid);
        model.addAttribute("article", article);
        return "article/show";
    }
}
