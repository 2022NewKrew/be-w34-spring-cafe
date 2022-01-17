package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.ArticleViewDTO;
import com.kakao.cafe.article.dto.DetailArticleViewDTO;
import com.kakao.cafe.article.dto.QuestionDTO;
import com.kakao.cafe.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/questions")
    public String question(QuestionDTO questionDTO) {
        articleService.question(questionDTO);

        return "redirect:/";
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ArticleViewDTO> articleList = articleService.getAllArticles().stream()
                .map(ArticleViewDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("questions", articleList);

        return "index";
    }

    @GetMapping("/articles/{index}")
    public String getArticleById(@PathVariable("index") Long id, Model model) {
        model.addAttribute("article", new DetailArticleViewDTO(articleService.findById(id)));

        return "qna/show";
    }
}
