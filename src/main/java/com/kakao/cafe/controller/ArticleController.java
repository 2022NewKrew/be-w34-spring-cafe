package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.SessionUser;
import com.kakao.cafe.controller.interceptor.Authenticated;
import com.kakao.cafe.domain.article.ArticleService;
import com.kakao.cafe.domain.article.dto.ArticleRepliesResponseDto;
import com.kakao.cafe.domain.article.dto.ArticleRequestDto;
import com.kakao.cafe.domain.article.dto.ArticleSimpleResponseDto;
import com.kakao.cafe.domain.article.dto.ReplyRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Authenticated
@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles/{articleId}")
    public String getArticle(@PathVariable Long articleId, Model model) {
        ArticleRepliesResponseDto article = articleService.retrieveArticleWithReplies(articleId);
        model.addAttribute("article", article);
        return "article/show";
    }

    @PostMapping("/articles")
    public String createArticle(ArticleRequestDto requestDto, SessionUser sessionUser) {
        articleService.createArticle(requestDto, sessionUser.getUserId());
        return "redirect:/";
    }

    @GetMapping("/articles/form")
    public String createArticleForm() {
        return "article/form";
    }

    @GetMapping("/articles/{articleId}/update")
    public String updateArticleForm(@PathVariable Long articleId, Model model, SessionUser sessionUser) {
        ArticleSimpleResponseDto article = articleService.retrieveArticleForUpdate(articleId, sessionUser.getUserId());
        model.addAttribute("article", article);
        return "article/updateForm";
    }

    @PutMapping("/articles/{articleId}")
    public String updateArticle(@PathVariable Long articleId, ArticleRequestDto requestDto, SessionUser sessionUser) {
        articleService.updateArticle(articleId, sessionUser.getUserId(), requestDto);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/articles/{articleId}")
    public String deleteArticle(@PathVariable Long articleId, SessionUser sessionUser) {
        articleService.deleteArticle(articleId, sessionUser.getUserId());
        return "redirect:/";
    }

    @PostMapping("/articles/{articleId}/reply")
    public String createReply(@PathVariable Long articleId, ReplyRequestDto replyRequestDto, SessionUser sessionUser) {
        articleService.createReply(articleId, replyRequestDto, sessionUser.getUserId());
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/articles/{articleId}/reply/{replyId}")
    public String deleteReply(@PathVariable Long articleId, @PathVariable Long replyId, SessionUser sessionUser) {
        articleService.deleteReply(articleId, replyId, sessionUser.getUserId());
        return "redirect:/articles/" + articleId;
    }
}
