package com.kakao.cafe.controller;

import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ArticleRequest;
import com.kakao.cafe.dto.article.ArticleUpdateRequest;
import com.kakao.cafe.exception.UnauthorizedAccessException;
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
    public String publish(ArticleRequest articleRequest, HttpSession session) {
        Auth auth = (Auth) session.getAttribute("auth");
        if (auth == null) {
            return "redirect:/user/login";
        }
        articleService.publishArticle(articleRequest);

        return "redirect:/";
    }

    @GetMapping("/{articleId}")
    public String getArticle(@PathVariable Long articleId, Model model, HttpSession session) {
        Auth auth = (Auth) session.getAttribute("auth");
        if (auth == null) {
            return "redirect:/user/login";
        }
        ArticleDto article = articleService.findById(articleId);
        model.addAttribute("article", article);

        return "article/show";
    }

    @GetMapping("/{articleId}/form")
    public String updateForm(@PathVariable Long articleId, Model model, HttpSession session) {
        Auth auth = (Auth) session.getAttribute("auth");
        ArticleDto article = articleService.findById(articleId);
        if (auth == null || !auth.validateById(article.getAuthorId())) {
            throw new UnauthorizedAccessException("인가되지 않은 접근입니다.");
        }
        model.addAttribute("article", article);

        return "article/updateForm";
    }

    @PutMapping("/{articleId}/update")
    public String updateArticle(@PathVariable Long articleId, ArticleUpdateRequest articleUpdateRequest, HttpSession session) {
        Auth auth = (Auth) session.getAttribute("auth");
        ArticleDto article = articleService.findById(articleId);
        if (auth == null || !auth.validateById(article.getAuthorId())) {
            throw new UnauthorizedAccessException("인가되지 않은 접근입니다.");
        }
        articleService.update(articleId, articleUpdateRequest);

        return "redirect:/articles/{articleId}";
    }

    @DeleteMapping("/{articleId}")
    public String deleteArticle(@PathVariable Long articleId, HttpSession session) {
        Auth auth = (Auth) session.getAttribute("auth");
        ArticleDto article = articleService.findById(articleId);
        if (auth == null || !auth.validateById(article.getAuthorId())) {
            throw new UnauthorizedAccessException("인가되지 않은 접근입니다.");
        }
        articleService.delete(articleId);

        return "redirect:/";
    }
}
