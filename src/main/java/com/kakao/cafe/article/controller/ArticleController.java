package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.request.ArticleReqDto;
import com.kakao.cafe.article.dto.response.ArticleDetailResDto;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.exception.SessionUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public String saveArticle(@ModelAttribute ArticleReqDto articleReqDto) {
        articleService.saveArticle(articleReqDto);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable Long id, Model model, HttpSession session) {
        checkSessionUser(session);

        ArticleDetailResDto article = articleService.getArticle(id);
        model.addAttribute("article", article);

        return "/qna/show";
    }

    @GetMapping("/form")
    public String showArticleForm(HttpSession session) {
        checkSessionUser(session);

        return "/qna/form";
    }

    private void checkSessionUser(HttpSession session) {
        if (session.getAttribute("sessionUserId") == null) {
            throw new SessionUserNotFoundException();
        }
    }
}
