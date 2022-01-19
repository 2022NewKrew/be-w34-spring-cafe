package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.UserAuthorized;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ArticleRequest;
import com.kakao.cafe.dto.article.ArticleUpdateRequest;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getArticle(@PathVariable Long articleId, Model model) {
        ArticleDto article = articleService.findById(articleId);
        model.addAttribute("article", article);

        return "article/show";
    }

    @UserAuthorized
    @GetMapping("/{articleId}/form")
    public String updateForm(@PathVariable Long articleId, Model model) {
        ArticleDto article = articleService.findById(articleId);
        model.addAttribute("article", article);

        return "article/updateForm";
    }

    @UserAuthorized
    @PutMapping("/{articleId}")
    public String updateArticle(@PathVariable Long articleId, ArticleUpdateRequest articleUpdateRequest) {
        articleService.update(articleId, articleUpdateRequest);

        return "redirect:/articles/{articleId}";
    }

    @UserAuthorized
    @DeleteMapping("/{articleId}")
    public String deleteArticle(@PathVariable Long articleId) {
        articleService.delete(articleId);

        return "redirect:/";
    }
}
