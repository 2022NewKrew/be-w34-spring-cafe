package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {
    ArticleService articleService = new ArticleService();

    //새로운 질문 생성
    @PostMapping(value = "/qna/create")
    public String createArticle(Article article, Model model){
        articleService.articleCreate(article);
        return "redirect:/";
    }

    //index.html에 노출되는 질문리스트
    @GetMapping(value = {"/", "/index.html"})
    public String showArticleList(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());

        return "index";
    }

    //상세 질문글 확인
    @GetMapping(value = "/qnas/{sequence}")
    public String showArticle(@PathVariable("sequence") Long sequence, Model model){
        Article article = articleService.getArticle(sequence - 1);
        model.addAttribute("name", article.getName());
        model.addAttribute("title", article.getTitle());
        model.addAttribute("date", article.getDate());
        model.addAttribute("contents", article.getContents());
        return "/qna/show";
    }
}
