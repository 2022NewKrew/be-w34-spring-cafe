package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
    public String postCreateArticle(String title, String writerUserId, String contents) {
        final User writer = userService.getUserByUserId(writerUserId);
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
