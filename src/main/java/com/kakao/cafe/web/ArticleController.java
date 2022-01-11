package com.kakao.cafe.web;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/qna/create")
    public String createQuestion(Article article) {
        articleService.createQuestion(article);
        return "redirect:/";
    }
}
