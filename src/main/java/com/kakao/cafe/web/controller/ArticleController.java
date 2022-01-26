package com.kakao.cafe.web.controller;

import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.web.dto.ArticleRequest;
import com.kakao.cafe.web.dto.ArticleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/article/create")
    public String postCreateArticle(ArticleRequest articleRequest) {
        articleService.createArticle(articleRequest);
        return "redirect:/index";
    }

    @GetMapping(value = "/index")
    public String getArticleList(Model model) {
        log.info("articleList:{}", articleService.getArticleList());
        model.addAttribute("articleListSize", articleService.getArticleListSize());
        model.addAttribute("articleList", articleService.getArticleList());
        return "/index";
    }

    @GetMapping(value = "/articles/{index}")
    public String getArticleShow(@PathVariable int index, Model model) {
        ArticleResponse articleResponse = articleService.getArticleByIndex(index);
        model.addAttribute("article", articleResponse);
        return "/article/show";
    }
}
