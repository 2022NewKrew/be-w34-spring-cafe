package com.kakao.cafe.controller;

import com.kakao.cafe.dto.article.ArticleRegistrationForm;
import com.kakao.cafe.dto.article.ArticleShowFormWithContents;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/questions")
    public String createQuestion(@RequestParam("writer") String writerName,
                                 @RequestParam("title") String title,
                                 @RequestParam("contents") String contents) {
        ArticleRegistrationForm articleRegistrationForm = new ArticleRegistrationForm(writerName, title, contents);
        articleService.createArticle(articleRegistrationForm);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showArticleList(Model model) {
        model.addAttribute("articles", articleService.getArticleList());
        return "index";
    }

    @GetMapping("articles/{articleId}")
    public String showArticle(@PathVariable("articleId") Long articleId, Model model) {
        ArticleShowFormWithContents articleShowFormWithContents = articleService.getArticleById(articleId);
        model.addAttribute("article", articleShowFormWithContents);
        return "qna/show";
    }
}
