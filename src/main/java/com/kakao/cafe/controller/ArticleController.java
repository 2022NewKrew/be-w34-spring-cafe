package com.kakao.cafe.controller;

import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ArticleRequest;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public String publish(ArticleRequest articleRequest) {
        articleService.publishArticle(articleRequest);

        return "redirect:/";
    }

    @GetMapping("/{articleId}")
    public String getArticle(@PathVariable Long articleId, Model model, HttpSession session) {
        Auth auth = (Auth) session.getAttribute("auth");
        if (auth == null) {
            return "redirect:/";
        }
        ArticleDto article = articleService.findById(articleId);
        model.addAttribute("article", article);

        return "article/show";
    }
}
