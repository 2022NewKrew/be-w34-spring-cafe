package com.kakao.cafe.controller;

import com.kakao.cafe.dto.CreateArticleDto;
import com.kakao.cafe.dto.CreateArticleRequestDto;
import com.kakao.cafe.dto.FindArticleDto;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("article/form")
    public String getArticleForm() {
        return "article/form";
    }

    @GetMapping("/")
    public String showArticles(Model model) {
        model.addAttribute("articles", this.articleService.getAll());
        return "index";
    }

    @PostMapping("/articles")
    public String createArticle(@ModelAttribute CreateArticleRequestDto createArticleRequestDto) {
        CreateArticleDto createArticleDto = new CreateArticleDto(
            createArticleRequestDto.getTitle(),
            createArticleRequestDto.getContent()
        );
        this.articleService.createArticle(createArticleDto);
        return "redirect:/";
    }

    @GetMapping("articles/{articleId}")
    public String getArticle(@PathVariable String articleId, Model model) {
        FindArticleDto findArticleDto = new FindArticleDto(Integer.parseInt(articleId));
        this.articleService.getArticleById(findArticleDto)
            .ifPresent(article -> model.addAttribute("article", article));
        return "article/show";
    }
}
