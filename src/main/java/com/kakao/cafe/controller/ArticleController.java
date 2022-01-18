package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.CheckAuth;
import com.kakao.cafe.domain.Auth;
import com.kakao.cafe.domain.dtos.ArticleResponseDto;
import com.kakao.cafe.domain.dtos.ArticleSaveDto;
import com.kakao.cafe.service.ArticleService;
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

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
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
            @SessionAttribute(SessionName.AUTH) Auth auth
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/articles/post";
        }
        dto.setWriter(auth.getName());
        articleService.save(dto);
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    @CheckAuth
    public String articlePage(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "qna/show";
    }

    @PutMapping("/{id}")
    public String updateArticle(
            @Valid @ModelAttribute ArticleSaveDto dto,
            BindingResult bindingResult,
            @PathVariable Long id
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/articles/{id}/edit";
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
        ArticleResponseDto article = articleService.findById(id);
        if (!article.getWriter().equals(auth.getName())) {
            return "redirect:/login";
        }
        articleService.deleteById(id);
        return "redirect:/articles";
    }

    @GetMapping("/{id}/edit")
    @CheckAuth
    public String updateArticlePage(
            @PathVariable Long id,
            Model model,
            @SessionAttribute(SessionName.AUTH) Auth auth
    ) {
        ArticleResponseDto article = articleService.findById(id);
        if (!article.getWriter().equals(auth.getName())) {
            return "qna/permissionError";
        }
        model.addAttribute("article", article);
        return "qna/updateForm";
    }
}
