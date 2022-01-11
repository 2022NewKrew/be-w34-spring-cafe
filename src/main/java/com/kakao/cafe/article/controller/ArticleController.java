package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.article.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    private final ArticleService articleService = new ArticleService();

    @GetMapping(path = "/")
    public String home() {
        return "redirect:/articles";
    }

    @GetMapping("/articles")
    public String getAllArticles(Model model) {
        model.addAttribute("articles", articleService.findArticles());
        return "index";
    }

    @GetMapping("/articles/{articleId}")
    public String getOneArticle(Model model, @PathVariable long articleId) {
        Article article = articleService.findById(articleId);
        if (article == null) {
            return "redirect:/";
        }
        model.addAttribute("articleInfo", article);
        return "qna/show";
    }

    //----------------------

    @PostMapping("/articles")
    public String createUser(String title, String contents, String writer) {
        contents.replace("\r\n", "<br>");
        Article article = new Article(0, title, contents, writer);
        articleService.addArticle(article);

        return "redirect:/articles/" + article.getId();
    }
}
