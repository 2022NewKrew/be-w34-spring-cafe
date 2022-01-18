package com.kakao.cafe.web.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleApiController {
    private final ArticleService articleService;

    private ArticleApiController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    String getArticles(Model model) {
        List<Article> articleList = articleService.getArticles();
        model.addAttribute("articles", articleList);
        return "index";
    }

    @GetMapping("/articles/form")
    String getForm(HttpSession session) {
        Users user = (Users) session.getAttribute("sessionedUser");
        if (user == null)
            return "redirect:/";
        return "articles/form";
    }

    @GetMapping("/articles/{id}")
    String findById(@PathVariable int id, Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("sessionedUser");
        if (user == null)
            return "redirect:/";
        Article article = articleService.getByArticleId(id);
        model.addAttribute("article", article);
        return "articles/show";
    }

    @PostMapping("/articles")
    String createArticle(Article article, HttpSession session) {
        Users user = (Users) session.getAttribute("sessionedUser");
        if (user == null)
            return "redirect:/";
        article.setAuthorId(user.getId());
        articleService.addArticle(article);
        return "redirect:/";
    }
}
