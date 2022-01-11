package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleForm;
import com.kakao.cafe.dto.UserForm;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/qna/form")
    public String createForm(UserForm form) {
        return "qna/form";
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


}
