package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.request.ArticleReqDto;
import com.kakao.cafe.article.dto.response.ArticleDetailResDto;
import com.kakao.cafe.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getArticle(@PathVariable Long id, Model model) {
        ArticleDetailResDto article = articleService.getArticle(id);
        model.addAttribute("article", article);

        return "/qna/show";
    }
}
