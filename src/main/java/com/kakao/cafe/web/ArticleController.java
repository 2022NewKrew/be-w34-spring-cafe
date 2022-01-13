package com.kakao.cafe.web;

import com.kakao.cafe.article.ArticleService;
import com.kakao.cafe.article.Articles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService = new ArticleService();

    @GetMapping
    public String getArticles(Model model) {
        List<Articles> articleList = articleService.getArticleList();
        model.addAttribute("articleList", articleList);
        return "article/list";
    }

    @GetMapping("/form")
    public String getForm() { return "article/form"; }

    @GetMapping("/{id}")
    public String getArticleById(@PathVariable Long id, Model model) {
        Articles article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "article/show";
    }


    @PostMapping
    String createUser(Articles article) {
        articleService.createArticle(article);
        return "redirect:/articles";
    }
}
