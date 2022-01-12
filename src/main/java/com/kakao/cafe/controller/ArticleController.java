package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.ArticleForm;
import com.kakao.cafe.dto.UserForm;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String findArticleList(Model model) {
        List<Article> articles = articleService.findArticleList();
        model.addAttribute("articles" , articles);
        return "index";
    }

    @PostMapping("/questions")
    public String create(ArticleForm form) {
        Article article = new Article();
        article.setWriter(form.getWriter());
        article.setTitle(form.getTitle());
        article.setContents(form.getContents());
        articleService.save(article);
        return "redirect:/";
    }
    @GetMapping("/articles/{id}")
    public String findUser(@PathVariable("id") Long articleId, Model model) {
        Article article = articleService.findArticle(articleId);
        model.addAttribute("article" , article);
        System.out.println(article.toString());
        return "qna/show";
    }

}
