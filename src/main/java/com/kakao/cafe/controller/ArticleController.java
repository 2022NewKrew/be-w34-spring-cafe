package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.UserAuthorized;
import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.dto.article.ArticleDetailDto;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ArticleRequest;
import com.kakao.cafe.dto.article.ReplyRequest;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.kakao.cafe.annotation.UserAuthorized.AuthCode.*;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @UserAuthorized(target = SESSION)
    @PostMapping
    public String publish(ArticleRequest articleRequest, HttpSession session) {
        Auth auth = (Auth) session.getAttribute("auth");
        articleService.publishArticle(auth.getAuthId(), articleRequest);

        return "redirect:/";
    }

    @UserAuthorized(target = SESSION)
    @PostMapping("/{articleId}/reply")
    public String createReply(@PathVariable Long articleId, ReplyRequest replyRequest, HttpSession session) {
        Auth auth = (Auth) session.getAttribute("auth");
        articleService.saveComment(articleId, auth.getAuthId(), replyRequest);

        return "redirect:/articles/{articleId}";
    }

    @UserAuthorized(target = SESSION)
    @GetMapping("/{articleId}")
    public String getArticle(@PathVariable Long articleId, Model model) {
        ArticleDetailDto article = articleService.findArticleDetailById(articleId);
        model.addAttribute("article", article);

        return "article/show";
    }

    @UserAuthorized(target = {SESSION, ARTICLE})
    @GetMapping("/{articleId}/form")
    public String updateForm(@PathVariable Long articleId, Model model) {
        ArticleDto article = articleService.findArticleById(articleId);
        model.addAttribute("article", article);

        return "article/updateForm";
    }

    @UserAuthorized(target = {SESSION, ARTICLE})
    @PutMapping("/{articleId}")
    public String updateArticle(@PathVariable Long articleId, ArticleRequest articleRequest) {
        articleService.updateArticle(articleId, articleRequest);

        return "redirect:/articles/{articleId}";
    }

    @UserAuthorized(target = {SESSION, ARTICLE})
    @DeleteMapping("/{articleId}")
    public String deleteArticle(@PathVariable Long articleId, HttpSession session) {
        Auth auth = (Auth) session.getAttribute("auth");
        articleService.deleteArticle(articleId, auth.getAuthId());

        return "redirect:/";
    }

    @UserAuthorized(target = {SESSION, REPLY})
    @DeleteMapping("/{articleId}/reply/{replyId}")
    public String deleteReply(@PathVariable Long replyId) {
        articleService.deleteReply(replyId);

        return "redirect:/articles/{articleId}";
    }
}
