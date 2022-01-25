package com.kakao.cafe.controller;

import com.kakao.cafe.controller.auth.AuthControl;
import com.kakao.cafe.controller.page.PageControl;
import com.kakao.cafe.domain.PageSelector;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.CommentDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.CommentService;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Controller
public class ArticleController {
    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;
    private final CommentService commentService;
    private final UserService userService;

    ArticleController(
            ArticleService articleService,
            CommentService commentService,
            UserService userService
    )
    {
        this.articleService = Objects.requireNonNull(articleService);
        this.commentService = Objects.requireNonNull(commentService);
        this.userService = Objects.requireNonNull(userService);
    }

    @GetMapping("/pages/{page}")
    public String getArticles(
            final HttpServletResponse response,
            final Model model,
            @PathVariable("page") final int page
    )
    {
        if (page <= 0) {
            return "redirect:/pages/1";
        }
        // get count and check idx and actualPage
        // if actualPage < idx, redirect to actualPage
        model.addAttribute("page_selector", new PageSelector(page, 150));
        // get only actualPage's articles
        model.addAttribute("articles", articleService.getDtoList());

        // force update lastPage cookie value
        response.addCookie(PageControl.createLastPageCookie(page));

        return "articles/index";
    }

    // / -> redirect:/pages/1

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

        if (checkNotOwner(request, articleDto.getUserId())) {
            return "error/400";
        }

        articleService.add(articleDto);
        logger.info("New Article added -  " + articleDto.getTitle());
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
        model.addAttribute("lastPage", PageControl.getLastPage(request));

        final String currentUserId = AuthControl.getLogonId(request);
        try {
            final ArticleDto articleDto = articleService.getDto(idx, currentUserId);
            model.addAttribute("article", articleDto);
        } catch (NoSuchElementException e) {
            return "articles/detail";
        }

        final List<CommentDto> comments = commentService.getDtoList(idx, currentUserId);
        model.addAttribute("comments", comments);
        model.addAttribute("totalComments", comments.size());

        return "articles/detail";
    }

    // GET /editArticleFailedNoPerm -> articles/editFailedNoPerm

    @GetMapping("/articles/edit/{idx}")
    public String editArticle(
            final HttpServletRequest request,
            @PathVariable("idx") @NonNull final long idx,
            final Model model
    )
    {
        if (checkNotLogin(request)) {
            return getRedirectLoginWithMsg(request);
        }

        ArticleDto articleDto;
        try {
            articleDto = articleService.getDto(idx);
        } catch (NoSuchElementException e) {
            // handle error in mustache
            return "articles/detail";
        }

        if (checkNotOwner(request, articleDto.getUserId())) {
            return "redirect:/editArticleFailedNoPerm";
        }

        model.addAttribute("article", articleDto);
        return "articles/edit";
    }

    @PutMapping("/articles")
    public String editArticle(
            final HttpServletRequest request,
            @NonNull final ArticleDto articleDto
    )
    {
        if (checkNotLogin(request)) {
            return getRedirectLoginWithMsg(request);
        }

        String articleOwnerId;
        try {
            articleOwnerId = articleService.getDto(articleDto.getIdx())
                    .getUserId();
        } catch (NoSuchElementException e) {
            return "error/404";
        }

        if (checkNotOwner(request, articleOwnerId)) {
            return "redirect:/editArticleFailedNoPerm";
        }

        if (articleService.update(articleDto)) {
            return "redirect:/articles/" + articleDto.getIdx();
        }

        return "error/500";
    }

    // GET /delArticleFailedOthersCommentExist -> articles/delFailedOthersCommentExist

    @DeleteMapping("/articles/{idx}")
    public String deleteArticle(
            final HttpServletRequest request,
            @PathVariable("idx") final long idx
    )
    {
        if (checkNotLogin(request)) {
            return getRedirectLoginWithMsg(request);
        }

        String articleOwnerId;
        try {
            articleOwnerId = articleService.getDto(idx)
                    .getUserId();
        } catch (NoSuchElementException e) {
            return "error/404";
        }

        if (checkNotOwner(request, articleOwnerId)) {
            return "redirect:/editArticleFailedNoPerm";
        }

        if (articleService.delete(idx)) {
            logger.info("Article deleted - " + idx);
            return "redirect:/";
        }

        return "redirect:/delArticleFailedOthersCommentExist";
    }

    private boolean checkNotLogin(final HttpServletRequest request) {
        return !AuthControl.isLogon(request, userService);
    }

    private String getRedirectLoginWithMsg(final HttpServletRequest request) {
        request.getSession()
                .setAttribute(UserController.TAG_LOGIN_ERROR, UserController.MSG_REQUIRE_LOGIN);
        return "redirect:/login";
    }

    private boolean checkNotOwner(final HttpServletRequest request, final String articleUserId) {
        final String curUserId = AuthControl.getLogonId(request);
        if (curUserId == null) {
            return true;
        }
        return !curUserId.equals(articleUserId);
    }
}
