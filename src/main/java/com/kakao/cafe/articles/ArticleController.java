package com.kakao.cafe.articles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public String createArticle(ArticleRequest articleRequest) {
        articleService.createArticle(articleRequest);

        return "redirect:/articles";
    }

    @GetMapping
    public String getArticles(Model model) {
        List<ArticleDto> articleDtoList = articleService.getArticles();

        model.addAttribute("articles", articleDtoList);

        return "index";
    }

    @GetMapping("/{id}")
    public String getArticleById(@PathVariable("id") Long id, Model model) {
        ArticleDto articleDto = articleService.getArticleById(id);
        model.addAttribute("article", articleDto);

        return "qna/show";
    }
}
