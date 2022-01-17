package com.kakao.cafe.web;

import com.kakao.cafe.dto.article.ArticleContents;
import com.kakao.cafe.dto.article.ArticleCreateCommand;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String listArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "/index";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        ArticleContents articleContents = articleService.getArticle(id);
        model.addAttribute("article", articleContents);
        return "/qna/show";
    }

    @PostMapping("/questions")
    public String addArticle(String writer, String title, String contents) {
        articleService.createArticle(new ArticleCreateCommand(writer, title, contents));
        return "redirect:/";
    }
}
