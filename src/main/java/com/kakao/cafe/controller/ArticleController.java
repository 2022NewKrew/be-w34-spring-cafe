package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController {
    ArticleService articleService = new ArticleService();

    //새로운 질문 생성
    @PostMapping(value = "/qna/create")
    public String createArticle(Article article, Model model){
        articleService.articleCreate(article);
        return "redirect:/index.html";
    }

    //index.html에 노출되는 질문리스트
    @RequestMapping(value = {"/", "index.html"})
    public String showArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());

        return "index";
    }
}
