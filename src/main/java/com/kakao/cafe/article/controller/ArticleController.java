package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.QuestionDTO;
import com.kakao.cafe.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/question")
    public String question(QuestionDTO questionDTO) {
        articleService.question(questionDTO);

        return "redirect:/";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("questions", articleService.getAllArticles());

        return "index";
    }
}
