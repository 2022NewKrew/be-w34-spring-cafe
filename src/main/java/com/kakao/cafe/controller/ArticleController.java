package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Resource(name = "articleService")
    private ArticleService articleService;

    private static final String NOT_LOGGED_IN_MESSAGE = "로그인후 이용해 주세요!";

    @GetMapping
    String posts(Model model) {
        model.addAttribute("articles", articleService.getArticleList());
        return "index";
    }

    @GetMapping("/articles/form")
    String form(HttpSession session, Model model) {
        if (session.getAttribute("sessionUser") == null) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, NOT_LOGGED_IN_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        return "qna/form";
    }

    @PostMapping("/articles")
    String articles(@Valid ArticleDTO article) {
        articleService.insertArticle(article);
        logger.info("create Article -> Writer : {}, Title : {}", article.getWriterId(), article.getTitle());
        return "redirect:/";
    }

    @GetMapping("/articles/{articleId}")
    String show(@PathVariable long articleId, Model model) {
        articleService.increaseViews(articleId);
        ArticleDTO article = articleService.getArticleById(articleId);
        model.addAttribute("article", article);
        model.addAttribute("articleId", articleId);
        logger.info("get Article -> articleId : {}", articleId);
        return "qna/show";
    }

}
