package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.SessionCheck;
import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.dto.SessionUserDTO;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Resource(name = "articleService")
    private ArticleService articleService;

    @GetMapping
    String articles(Long page, Model model) {
        articleService.getArticleList(page, model);
        return "article/list";
    }

    @PostMapping
    @SessionCheck
    String insertArticle(@Valid ArticleDTO article) {
        articleService.insertArticle(article);
        return "redirect:/";
    }

    @GetMapping("/form")
    @SessionCheck
    String form() {
        return "article/form";
    }

    @GetMapping("/{id}/form")
    @SessionCheck
    String getArticleForm(@PathVariable long id, Model model, SessionUserDTO sessionUser) {
        articleService.getArticleForm(id, sessionUser, model);
        return "article/updateForm";
    }

    @PutMapping("/{id}/update")
    @SessionCheck
    String updateArticle(@PathVariable long id, @Valid ArticleDTO article, SessionUserDTO sessionUser) {
        articleService.updateArticle(sessionUser, id, article);
        return "redirect:/";
    }

    @DeleteMapping("/{id}/delete")
    @SessionCheck
    String deleteArticle(@PathVariable long id, SessionUserDTO sessionUser) {
        articleService.deleteArticle(id, sessionUser);
        return "redirect:/";
    }

    @GetMapping("/{articleId}")
    @SessionCheck
    String show(@PathVariable long articleId, Model model, SessionUserDTO sessionUser) {
        articleService.getArticle(articleId, sessionUser, model);
        return "article/show";
    }


}
