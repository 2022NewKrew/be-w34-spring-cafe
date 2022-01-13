package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticleRequestDto;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/qna/create")
    public String createArticle(ArticleRequestDto articleRequestDto) {
        articleService.createArticle(articleRequestDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getArticleList(Model model) {
        List<ArticleDto> articleList = articleService.getArticleList();
        model.addAttribute("articleList", articleList);
        return "index";
    }

    @GetMapping("/articles/{index}")
    public String getArticleInfo(@PathVariable String index, Model model) {
        ArticleDto article = articleService.findById(index);
        model.addAttribute("article", article);
        return "qna/show";
    }
}
