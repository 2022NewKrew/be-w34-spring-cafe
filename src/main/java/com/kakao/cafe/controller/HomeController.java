package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    // 홈 화면 (질문 리스트 페이지)
    @GetMapping("/")
    public String home(Model model){
        return "redirect:/articles";
    }
}
