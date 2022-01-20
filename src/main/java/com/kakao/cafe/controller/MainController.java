package com.kakao.cafe.controller;

import com.kakao.cafe.dto.article.ArticlePageDto;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {

    private final ArticleService articleService;

    public MainController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String index(@RequestParam Optional<Integer> page, Model model) {
        int offset = page.orElse(0);
        ArticlePageDto articlePage = articleService.getArticlePage(offset);
        model.addAttribute("articlePage", articlePage);

        return "index";
    }
}
