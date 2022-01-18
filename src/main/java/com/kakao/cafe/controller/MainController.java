package com.kakao.cafe.controller;

import com.kakao.cafe.domain.model.User;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ArticleService articleService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("articles", articleService.findAllArticles());
        return "/index";
    }
}
