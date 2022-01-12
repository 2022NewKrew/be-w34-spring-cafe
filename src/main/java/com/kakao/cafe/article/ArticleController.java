package com.kakao.cafe.article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @PostMapping("/articles")
    public String processCreationForm(Article article) {
        logger.info("Articles before: " + Article.getArticles());
        Article.addArticle(article);
        logger.info("Articles after: " + Article.getArticles());
        return "redirect:/articles";
    }

    @GetMapping(value = {"/", "/articles"})
    public String listArticles(Model model) {
        model.addAttribute("articles", Article.getArticles());
        return "index";
    }

    @GetMapping("/articles/{id}")
    public String showArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", Article.findArticleById(id));
        return "article/show";
    }
}
