package com.kakao.cafe.home.controller;

import com.kakao.cafe.article.dto.response.ArticleResDto;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ArticleService articleService;
    private final UserService userService;

    @GetMapping
    public String index(Model model) {
        List<ArticleResDto> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "/index";
    }
}
