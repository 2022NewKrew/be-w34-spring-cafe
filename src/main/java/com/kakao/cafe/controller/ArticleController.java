package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleCreateDto;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    private static final int MAX_ARTICLES = 1;
    private static final int INDEX_OF_FIRST_ARTICLE = 0;

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("articles", articleService.getPartOfArticles(INDEX_OF_FIRST_ARTICLE, MAX_ARTICLES));
        model.addAttribute("pages", articleService.getPages(MAX_ARTICLES));
        return "index";
    }

    @GetMapping("/index/{page}")
    public String getIndexByPage(@PathVariable int page, Model model) {
        model.addAttribute("articles", articleService.getPartOfArticles((page - 1) * MAX_ARTICLES, page * MAX_ARTICLES));
        model.addAttribute("pages", articleService.getPages(MAX_ARTICLES));
        return "index";
    }

    @PostMapping("/articles")
    public String postArticle(ArticleCreateDto articleCreateDto) {
        articleService.createArticle(articleCreateDto);
        return "redirect:/";
    }

    @GetMapping("/articles/{id}")
    public String getArticleDetail(@PathVariable int id, Model model) {
        model.addAttribute("article", articleService.findArticleById(id));
        return "qna/show";
    }
}
