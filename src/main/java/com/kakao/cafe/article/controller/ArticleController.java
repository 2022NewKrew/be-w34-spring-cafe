package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleDto;
import com.kakao.cafe.article.dto.ArticleFormDto;
import com.kakao.cafe.article.dto.ArticleListDto;
import com.kakao.cafe.article.dto.ArticleMapper;
import com.kakao.cafe.article.service.ArticlePostService;
import com.kakao.cafe.article.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final ArticlePostService articlePostService;
    private final ArticleMapper articleMapper;

    public ArticleController(ArticleService articleService, ArticlePostService articlePostService, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.articlePostService = articlePostService;
        this.articleMapper = articleMapper;
    }

    @GetMapping("/")
    public String showArticles(Model module) {
        List<ArticleListDto> articles = articleMapper.toListArticleDto(articleService.findArticles());
        module.addAttribute("articles", articles);
        return "index";
    }

    @PostMapping("/question/create")
    public String createArticle(ArticleFormDto articleFormDto) {
        Article article = articleMapper.toArticle(articleFormDto);
        articlePostService.postArticle(article);
        return "redirect:/";
    }

    @GetMapping("/question/{index}")
    public String showArticle(@PathVariable Long index, Model module) {
        ArticleDto articles = articleMapper.toArticleDto(articleService.findArticleByArticleId(index));
        module.addAttribute("articles", articles);
        return "qna/show";
    }
}