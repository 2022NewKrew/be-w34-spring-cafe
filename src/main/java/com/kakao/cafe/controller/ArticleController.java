package com.kakao.cafe.controller;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public String findArticleById(@PathVariable String id, Model model){
        Article article = articleService.findArticleById(id);
        model.addAttribute("article", article);
        return "article/view";
    }

    @GetMapping("/post")
    public String articlePostView(){
        return "article/post";
    }

    @PostMapping("/post")
    public String postArticle(@RequestParam String title, @RequestParam String content){
        articleService.save(title, content);
        return "redirect:/";
    }
}
