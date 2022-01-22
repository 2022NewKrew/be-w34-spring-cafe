package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleFormDto;
import com.kakao.cafe.dto.ArticleViewDto;
import com.kakao.cafe.mapper.ArticleMapper;
import com.kakao.cafe.service.ArticleService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/articles")
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
    private static final ArticleMapper ARTICLE_MAPPER = ArticleMapper.INSTANCE;

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping()
    public String postCreateArticle(@ModelAttribute ArticleFormDto articleFormDto) {
        final Article article = ARTICLE_MAPPER.convertToEntity(articleFormDto);
        final Article createdArticle = articleService.createArticle(article);
        LOGGER.info("POST request on createArticle -> {}", createdArticle);
        return "redirect:articles";
    }

    @GetMapping()
    public String getArticleList(Model model) {
        final List<ArticleViewDto> articleViewDtoList = articleService.getArticles().stream()
            .map(ARTICLE_MAPPER::convertToArticleViewDto)
            .collect(Collectors.toList());
        model.addAttribute("articles", articleViewDtoList);
        return "article/list";
    }

    @GetMapping("/{aid}")
    public String getArticleDetail(@PathVariable("aid") Integer aid, Model model) {
        final Article article = articleService.getArticleById(aid);
        final ArticleViewDto articleViewDto = ARTICLE_MAPPER.convertToArticleViewDto(article);
        model.addAttribute("article", articleViewDto);
        return "article/show";
    }
}
