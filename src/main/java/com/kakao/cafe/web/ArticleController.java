package com.kakao.cafe.web;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.web.dto.article.ArticleCreateRequestDto;
import com.kakao.cafe.web.dto.article.ArticleResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("article/create")
    public String articleWrite(ArticleCreateRequestDto articleCreateRequestDto) {
        logger.info("article: {}", articleCreateRequestDto);
        articleService.postArticle(Article.fromPost(articleCreateRequestDto.getTitle(), articleCreateRequestDto.getContent()));
        return "redirect:/";
    }

    @GetMapping("/")
    public String articleMain(Model model) {
        List<ArticleResponseDto> articleList = articleService.readAll();
        model.addAttribute("articles", articleList);
        model.addAttribute("size", articleList.size());
        return "index";
    }

    @GetMapping("article/{articleIndex}")
    public String articleDetail(@PathVariable String articleIndex, Model model) {
        logger.info("article Detail:{}", model.addAttribute("article", articleService.findById(articleIndex)));
        return "/article/show";
    }

}
