package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String getAllQuestions(Model model) {
        List<Article> articleList = articleService.getAllQuestions();
        model.addAttribute("articleList", articleList);
        return "qna/list";
    }

    @GetMapping("/articles/{id}")
    public String getQuestionsInfo(@PathVariable String id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("article", article);
        return "qna/show";
    }

    @PostMapping("/articles")
    public String addQuestions(@ModelAttribute Article article) {
        articleService.save(article);
        return "redirect:/";
    }

    @GetMapping("/articles/write")
    public String write() {
        return "qna/form";
    }

    @DeleteMapping("/articles/deleteByWriter")
    public String deleteByWriter(@RequestParam(value = "writer") String writer) {
        articleService.deleteByWriter(writer);
        return "redirect:/";
    }
}
