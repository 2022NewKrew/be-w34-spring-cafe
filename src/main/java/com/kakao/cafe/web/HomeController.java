package com.kakao.cafe.web;

import com.kakao.cafe.dto.ShowArticleDto;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ArticleService articleService;

    @Autowired
    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String Home(Model model) {
        List<ShowArticleDto> articleList = articleService.findAll();
        model.addAttribute("articleList", articleList);
        return "index";
    }
}
