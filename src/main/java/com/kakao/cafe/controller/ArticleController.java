package com.kakao.cafe.controller;

import com.kakao.cafe.controller.auth.AuthControl;
import com.kakao.cafe.domain.ArticleDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDto;
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

import javax.servlet.http.HttpSession;
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
    public String getArticles(final HttpSession session) {
        if (!AuthControl.isLogon(session, userService)) {
            session.setAttribute(UserController.TAG_LOGIN_ERROR, UserController.MSG_REQUIRE_LOGIN);
            return "redirect:/login";
        }
        return "articles/new";
    }

    @PostMapping("/articles")
    public String writeArticle(final HttpSession session, @NonNull final ArticleDto articleDto) {
        if (!AuthControl.isLogon(session, userService)) {
            session.setAttribute(UserController.TAG_LOGIN_ERROR, UserController.MSG_REQUIRE_LOGIN);
            return "redirect:/login";
        }

        final UserDto userDto = userService.getUser((String)session.getAttribute(AuthControl.TAG_ID));
        articleDto.setUserId(userDto.getId());
        articleDto.setUserName(userDto.getName());

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
