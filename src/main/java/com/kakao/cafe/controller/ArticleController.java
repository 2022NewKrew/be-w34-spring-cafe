package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDto;
import com.kakao.cafe.domain.UserDto;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;
import java.util.Objects;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private ArticleService articleService;

    @Autowired
    public void setArticleService(final ArticleService articleService) {
        this.articleService = Objects.requireNonNull(articleService);
    }

    @GetMapping("/")
    public String getArticles(final Model model) {
        model.addAttribute("articles", articleService.getList());
        return "index";
    }

    @GetMapping("/articles")
    public String getArticlesExplicit(final Model model) {
        return getArticles(model);
    }

    @GetMapping("/articles/new")
    public String newArticle() {
        return "articles/new";
    }

    @PostMapping("/articles")
    public String writeArticle(@NonNull final ArticleDto articleDto) {
        articleService.add(articleDto);
        logger.info("New Article added: " + articleDto.getTitle());
        return "redirect:/";
    }

    @GetMapping("/articles/{idx}/{title}")
    public String getArticleDetail(
            @PathVariable("idx") @NonNull final long idx,
            final Model model
    )
    {
        try {
            final ArticleDto articleDto = articleService.getArticle(idx);
            model.addAttribute("article", articleDto);
        } catch (NoSuchElementException ignored) {}

        return "articles/detail";
    }
}
