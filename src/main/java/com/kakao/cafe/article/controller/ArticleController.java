package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.model.ArticleRequest;
import com.kakao.cafe.article.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/question")
    public String registerArticle(ArticleRequest articleRequest){
        articleService.registerArticle(articleRequest);

        return "redirect:/";
    }

    @GetMapping("/form")
    public String getArticleFormPage(){
        return "/qna/form";
    }

    @GetMapping("/show")
    public String getArticleShowPage(){
        return "/qna/show";
    }
}
