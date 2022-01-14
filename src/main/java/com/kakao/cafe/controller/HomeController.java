package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.response.ArticleQueryResponseDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String getAllArticles(Model model) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles.stream()
                .map(ArticleQueryResponseDto::new)
                .collect(Collectors.toUnmodifiableList()));
        return "index";
    }
}
