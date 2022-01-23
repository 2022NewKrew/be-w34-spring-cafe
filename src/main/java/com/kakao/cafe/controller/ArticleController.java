package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.CheckAuth;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.domain.article.ArticleResponseDto;
import com.kakao.cafe.domain.article.ArticleSaveDto;
import com.kakao.cafe.domain.reply.ReplySaveDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import com.kakao.cafe.utils.SessionName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final ReplyService replyService;

    @Autowired
    public ArticleController(ArticleService articleService, ReplyService replyService) {
        this.articleService = articleService;
        this.replyService = replyService;
    }

    @GetMapping("/post")
    @CheckAuth
    public String createArticlePage() {
        return "qna/form";
    }

    @GetMapping("")
    public String articleListPage(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "qna/list";
    }

    @PostMapping("")
    @CheckAuth
    public String createArticle(
            @Valid @ModelAttribute ArticleSaveDto dto,
            BindingResult bindingResult,
            Model model,
            @SessionAttribute(SessionName.AUTH) Auth auth
    ) {
        if (!Utils.isValidBindingResult(bindingResult, model)) {
            return "qna/form";
        }
        dto.setUserId(auth.getId());
        articleService.save(dto);
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    @CheckAuth
    public String articlePage(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        model.addAttribute("replies", replyService.findAllByArticle(id));
        return "qna/show";
    }

    @PutMapping("/{id}")
    public String updateArticle(
            @Valid @ModelAttribute ArticleSaveDto dto,
            BindingResult bindingResult,
            Model model,
            @PathVariable Long id
    ) {
        if (!Utils.isValidBindingResult(bindingResult, model)) {
            model.addAttribute("articleId", id);
            model.addAttribute("article", dto);
            return "qna/updateForm";
        }
        articleService.update(id, dto);
        return "redirect:/articles/{id}";
    }

    @DeleteMapping("/{id}")
    @CheckAuth
    public String deleteArticle(
            @PathVariable Long id,
            @SessionAttribute(SessionName.AUTH) Auth auth
    ) {
        articleService.validateUserAndDeleteById(id, auth.getId());
        return "redirect:/articles";
    }

    @GetMapping("/{id}/edit")
    @CheckAuth
    public String updateArticlePage(
            @PathVariable Long id,
            Model model,
            @SessionAttribute(SessionName.AUTH) Auth auth
    ) {
        ArticleResponseDto article = articleService.validateUserAndGetById(id, auth.getId());
        model.addAttribute("articleId", id);
        model.addAttribute("article", article);
        return "qna/updateForm";
    }

    @PostMapping("/{id}/replies")
    @CheckAuth
    public String createReply(
            @PathVariable Long id,
            @Valid @ModelAttribute ReplySaveDto dto,
            BindingResult bindingResult,
            Model model,
            @SessionAttribute(SessionName.AUTH) Auth auth
    ) {
        if (!Utils.isValidBindingResult(bindingResult, model)) {
            model.addAttribute("article", articleService.findById(id));
            model.addAttribute("replies", replyService.findAllByArticle(id));
            return "qna/show";
        }
        dto.setUserId(auth.getId());
        dto.setArticleId(id);
        replyService.save(dto);
        return "redirect:/articles/{id}";
    }

    @DeleteMapping("/{id}/replies/{replyId}")
    @CheckAuth
    public String deleteReply(
            @PathVariable Long replyId,
            @SessionAttribute(SessionName.AUTH) Auth auth
    ) {
        replyService.validateUserAndDeleteById(replyId, auth.getId());
        return "redirect:/articles/{id}";
    }
}
