package com.kakao.cafe.web.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/articles/{id}/update")
    String getUpdateForm(@PathVariable int id, Model model, HttpSession session) {
        Article article = articleService.getByArticleId(id);
        Users user = (Users) session.getAttribute("sessionedUser");
        if (article.getAuthorId() != user.getId())
            throw new IllegalArgumentException("글쓴이가 일치하지 않습니다.");
        model.addAttribute("article", article);
        return "articles/updateForm";
    }

    @PutMapping("/articles/{id}")
    String updateArticle(@PathVariable int id, Article article, HttpSession session) {
        Users user = (Users) session.getAttribute("sessionedUser");
        if (user == null)
            throw new IllegalArgumentException("글쓴이가 일치하지 않습니다.");
        articleService.updateArticle(id, article);
        return "redirect:/articles/{id}";
    }

    @DeleteMapping("/articles/{id}")
    String deleteArticle(@PathVariable int id, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("sessionedUser");
        if (articleService.getByArticleId(id).getAuthorId() != currentUser.getId()) {
            throw new IllegalArgumentException("글쓴이가 일치하지 않습니다.");
        }
        articleService.deleteArticle(id);
        return "redirect:/";
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
