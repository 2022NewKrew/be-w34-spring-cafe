package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.SessionUser;
import com.kakao.cafe.domain.article.ArticleService;
import com.kakao.cafe.domain.article.dto.ArticleRequestDto;
import com.kakao.cafe.domain.article.dto.ArticleResponseDto;
import com.kakao.cafe.domain.user.exception.AnonymousUserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model, Optional<SessionUser> sessionUser) {
        SessionUser user = sessionUser.orElseThrow(() -> new AnonymousUserException("인증되지 않은 사용자입니다."));
        ArticleResponseDto article = articleService.retrieveArticle(id);
        model.addAttribute("article", article);
        return "article/show";
    }

    @PostMapping("/articles")
    public String createArticle(ArticleRequestDto requestDto, Optional<SessionUser> sessionUser) {
        SessionUser user = sessionUser.orElseThrow(() -> new AnonymousUserException("인증되지 않은 사용자 입니다."));
        articleService.createArticle(requestDto, user.getUserId());
        return "redirect:/";
    }

    @GetMapping("/articles/form")
    public String createArticleForm(Optional<SessionUser> sessionUser) {
        sessionUser.orElseThrow(() -> new AnonymousUserException("인증되지 않은 사용자입니다."));
        return "article/form";
    }

    @GetMapping("/articles/{articleId}/update")
    public String updateArticleForm(@PathVariable Long articleId, Model model, Optional<SessionUser> sessionUser) {
        SessionUser user = sessionUser.orElseThrow(() -> new AnonymousUserException("인증되지 않은 사용자 입니다."));
        ArticleResponseDto article = articleService.retrieveArticleForUpdate(articleId, user.getUserId());
        model.addAttribute("article", article);
        return "article/updateForm";
    }

    @PutMapping("/articles/{articleId}")
    public String updateArticle(@PathVariable Long articleId, ArticleRequestDto requestDto, Optional<SessionUser> sessionUser) {
        SessionUser user = sessionUser.orElseThrow(() -> new AnonymousUserException("인증되지 않은 사용자 입니다."));
        articleService.updateArticle(articleId, user.getUserId(), requestDto);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/articles/{articleId}")
    public String deleteArticle(@PathVariable Long articleId, Optional<SessionUser> sessionUser) {
        SessionUser user = sessionUser.orElseThrow(() -> new AnonymousUserException("인증되지 않은 사용자 입니다."));
        articleService.deleteArticle(articleId, user.getUserId());
        return "redirect:/";
    }

}
