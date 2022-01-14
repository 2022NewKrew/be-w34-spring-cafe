package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticleFormDto;
import com.kakao.cafe.dto.ArticleListDto;
import com.kakao.cafe.dto.ArticleMapper;
import com.kakao.cafe.service.ArticlePostService;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticlePostService articlePostService;

    @GetMapping("/question/form")
    public String showArticleForm() {
        return "qna/form";
    }

    @PostMapping("/question/create")
    public String createArticle(ArticleFormDto articleFormDto) {
        Article article = ArticleMapper.toArticle(articleFormDto);
        articlePostService.postArticle(article);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showArticles(Model module) {
        List<ArticleListDto> articles = ArticleMapper.toListArticleDto(articleService.findArticles());
        module.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("/articles/{index}")
    public String showArticle(@PathVariable Long index, Model module) {
        ArticleDto articles = ArticleMapper.toArticleDto(articleService.findArticleByArticleId(index));
        module.addAttribute("articles", articles);
        return "qna/show";
    }
}