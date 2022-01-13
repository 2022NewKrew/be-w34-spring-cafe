package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.response.ArticleQueryDetailResponseDto;
import com.kakao.cafe.controller.dto.request.ArticleRegisterRequestDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public String register(@ModelAttribute ArticleRegisterRequestDto articleRegisterRequestDto) {
        articleService.register(articleRegisterRequestDto);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("article", new ArticleQueryDetailResponseDto(article));
        return "/qna/show";
    }

}
