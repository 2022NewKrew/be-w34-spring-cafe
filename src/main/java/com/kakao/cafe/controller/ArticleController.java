package com.kakao.cafe.controller;

import com.kakao.cafe.controller.auth.AuthControl;
import com.kakao.cafe.dto.ArticleDto;
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

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.Objects;

@Controller
public class ArticleController {
    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;
    private final UserService userService;

    ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = Objects.requireNonNull(articleService);
        this.userService = Objects.requireNonNull(userService);
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
    public String getArticles(final HttpServletRequest request) {
        if (checkNotLogin(request)) {
            return getRedirectLoginWithMsg(request);
        }
        return "articles/new";
    }

    @PostMapping("/articles")
    public String writeArticle(final HttpServletRequest request, @NonNull final ArticleDto articleDto) {
        if (checkNotLogin(request)) {
            return getRedirectLoginWithMsg(request);
        }

        articleService.add(articleDto);
        logger.info("New Article added: " + articleDto.getTitle());
        return "redirect:/";
    }

    @GetMapping("/articles/{idx}")
    public String getArticleDetail(
            final HttpServletRequest request,
            @PathVariable("idx") @NonNull final long idx,
            final Model model
    )
    {
        if (checkNotLogin(request)) {
            return getRedirectLoginWithMsg(request);
        }

        try {
            final ArticleDto articleDto = articleService.getArticle(idx);
            model.addAttribute("article", articleDto);
        } catch (NoSuchElementException ignored) {}

        return "articles/detail";
    }

    private boolean checkNotLogin(final HttpServletRequest request) {
        if (!AuthControl.isLogon(request, userService)) {
            request.getSession()
                    .setAttribute(UserController.TAG_LOGIN_ERROR, UserController.MSG_REQUIRE_LOGIN);
            return true;
        }
        return false;
    }

    private String getRedirectLoginWithMsg(final HttpServletRequest request) {
        request.getSession()
                .setAttribute(UserController.TAG_LOGIN_ERROR, UserController.MSG_REQUIRE_LOGIN);
        return "redirect:/login";
    }
}
