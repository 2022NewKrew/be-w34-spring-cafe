package com.kakao.cafe.module.controller;

import com.kakao.cafe.module.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("")
    public String postArticle(ArticlePostDto articlePostDto){
        articleService.postArticle(articlePostDto);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showArticle(@PathVariable Long id, Model model){
        ArticleReadDto article = articleService.showArticle(id);
        model.addAttribute("article", article);
        return "article/show";
    }
}
