package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.response.ArticleQueryDetailResponseDto;
import com.kakao.cafe.controller.dto.request.ArticleRegisterRequestDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public String register(@Validated @ModelAttribute ArticleRegisterRequestDto articleRegisterRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("게시글 등록 실패");
            throw new IllegalArgumentException();
        }
        articleService.register(articleRegisterRequestDto);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("article", new ArticleQueryDetailResponseDto(article));
        return "/article/show";
    }

}
