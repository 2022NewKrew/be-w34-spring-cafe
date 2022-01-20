package com.kakao.cafe.controller;

import com.kakao.cafe.domain.model.Article;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Stream;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ArticleService articleService;
    private final UserService userService;

    @GetMapping("/")
    public String home(Model model){
        List<Article> articles = articleService.findAllArticles();
        model.addAttribute("articles", articles);
        return "/index";
    }
}
