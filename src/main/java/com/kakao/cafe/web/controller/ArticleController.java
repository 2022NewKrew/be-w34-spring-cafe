package com.kakao.cafe.web.controller;

import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.web.dto.ArticleDTO;
import com.kakao.cafe.web.dto.ArticleResponseDTO;
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
    public String postCreateArticle(ArticleDTO articleDTO) {
        articleService.createArticle(articleDTO);
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
        ArticleResponseDTO articleResponseDTO = articleService.getArticleByIndex(index);
        model.addAttribute("article", articleResponseDTO);
        return "/article/show";
    }
}
