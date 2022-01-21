package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.ArticleService;
import com.kakao.cafe.domain.article.dto.ArticleTableRowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String home(Model model) {
        final List<ArticleTableRowDto> articles = articleService.retrieveTableRows();
        model.addAttribute("articles", articles);
        return "/index";
    }
}
