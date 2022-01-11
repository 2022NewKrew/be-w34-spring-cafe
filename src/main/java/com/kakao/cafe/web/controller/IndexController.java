package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index(Model model) {
        List<Article> articles = ArticleController.getArticles();
        logger.info("GET /: response index page with {}", articles);
        model.addAttribute("articles", articles);
        return "index";
    }
}
