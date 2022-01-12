package com.kakao.cafe.controller;

import com.kakao.cafe.domain.ArticleDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    ArticleController(ArticleService articleService) {
        this.articleService = Objects.requireNonNull(articleService);
    }

    @GetMapping("/")
    public String getArticles(final Model model) {
        model.addAttribute("articles", articleService.getList());
        return "articles/index";
    }

    @GetMapping("/articles")
    public String getArticlesExplicit(final Model model) {
        return getArticles(model);
    }

    @GetMapping("/articles/new")
    public String newArticle(final Model model) {
        // (when login impl) getCurrentUser(); -> write userName attribute
        model.addAttribute("userName", User.NONE.getName());
        return "articles/new";
    }

    @PostMapping("/articles")
    public String writeArticle(@NonNull final ArticleDto articleDto) {
        // (when login impl) getCurrentUser(); -> update userId, userName in articleDto
        articleDto.setUserId(User.NONE.getId());
        articleDto.setUserName(User.NONE.getName());
        articleService.add(articleDto);
        logger.info("New Article added: " + articleDto.getTitle());
        return "redirect:/";
    }

    @GetMapping("/articles/{idx}")
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
