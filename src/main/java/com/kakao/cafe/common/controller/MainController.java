package com.kakao.cafe.common.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kakao.cafe.article.dto.response.ArticleFindResponseDTO;
import com.kakao.cafe.article.service.ArticleService;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ArticleService articleService;

    @GetMapping("/")
    public String mainPage(Model model) {
        List<ArticleFindResponseDTO> articles = articleService.getAllArticle();
        model.addAttribute("articles", articles);

        return "index";
    }
}
