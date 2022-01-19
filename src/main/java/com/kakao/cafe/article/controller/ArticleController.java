package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.ArticleDto;
import com.kakao.cafe.article.service.ArticleCreateService;
import com.kakao.cafe.article.service.ArticleFindService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/article")
public class ArticleController {
    private final ArticleCreateService articleCreateService;
    private final ArticleFindService articleFindService;

    public ArticleController(ArticleCreateService articleCreateService, ArticleFindService articleFindService) {
        this.articleCreateService = articleCreateService;
        this.articleFindService = articleFindService;
    }

    @PostMapping
    public String saveArticle(@Valid @RequestBody ArticleDto articleDto) {
        articleCreateService.save(articleDto);
        log.info("게시글 저장");
        return "index";
    }

    @GetMapping
    public String getArticleList(Model model) {
        model.addAttribute("articles", articleFindService.getArticleList());
        log.info("게시글 전체 조회");
        return "index";
    }

    @GetMapping("/{articleId}")
    public String getArticle(@PathVariable("articleId") Long id, Model model) {
        model.addAttribute("article", articleFindService.getArticle(id));
        log.info("게시글 상세 조회");
        return "index";
    }
}
