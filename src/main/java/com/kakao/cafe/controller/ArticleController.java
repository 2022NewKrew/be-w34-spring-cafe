package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/questions")
    public String uploadArticle(ArticleDto articleDto) {
        articleService.uploadArticle(articleDto);

        return "redirect:/";
    }

    @GetMapping("/")
    public String articleList(Model model) {
        List<ArticleDto> articleDtos = articleService.allArticles();
        model.addAttribute("articles", articleDtos);
        model.addAttribute("total", articleDtos.size());

        return "/index";
    }

    @GetMapping("questions/{id}")
    public String retrieveArticle(@PathVariable("id") Long articleId, Model model) {
        ArticleDto articleDto = articleService.retrieveArticle(articleId);
        model.addAttribute("article", articleDto);

        return "/post/show";
    }
}
