package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private static final List<Article> articles = new ArrayList<>();
    private static int sequence = 0;

    @PostMapping("/articles")
    public String createArticle(Article article) {
        logger.info("POST /articles: request {}", article);
        // article 생성
        article.setId(++sequence);
        articles.add(article);
        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String showArticle(Model model, @PathVariable int index) {
        if (index > articles.size()) {
            return "error/404";
        }
        Article article = articles.get(index - 1);
        logger.info("GET /articles/{}: response article detail page with {}", index, article);
        // article 조회
        model.addAttribute("article", article);
        return "article/show";
    }

    public static List<Article> getArticles() {
        return articles;
    }
}
