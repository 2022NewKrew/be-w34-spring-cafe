package com.kakao.cafe.controller;

import com.kakao.cafe.config.Mapper;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.dto.ArticleListDto;
import com.kakao.cafe.dto.InquireArticleDto;
import com.kakao.cafe.dto.PostArticleDto;
import com.kakao.cafe.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final Mapper mapper;

    @PostMapping("/questions")
    @LoginCheckAnnotation
    public String postArticle(@Valid PostArticleDto articleDto) {
        Article article = convertToArticle(articleDto);
        articleService.postArticle(article, articleDto.getWriter());
        return "redirect:/";
    }

    @GetMapping("/questions-form")
    @LoginCheckAnnotation
    public String getPostArticleForm() {
        return "qna/questions-form";
    }

    @GetMapping("/")
    public String articlesList(Model model) {
        List<ArticleListDto> articles = articleService.inquireAllArticles().stream()
                .map(this::convertToArticleListDto)
                .collect(Collectors.toList());
        model.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("/questions/{articleId}")
    @LoginCheckAnnotation
    public String inquireArticle(@PathVariable("articleId") @NotNull Long articleId, Model model) {
        Article article = articleService.inquireOneArticle(articleId);
        model.addAttribute("article", convertToInquireArticleDto(article));
        return "qna/show";
    }

    private Article convertToArticle(PostArticleDto articleDto) {
        return mapper.map(articleDto);
    }

    private InquireArticleDto convertToInquireArticleDto(Article article) {
        return mapper.map(article);
    }

    private ArticleListDto convertToArticleListDto(Article article) {
        return mapper.convertToList(article);
    }
}
