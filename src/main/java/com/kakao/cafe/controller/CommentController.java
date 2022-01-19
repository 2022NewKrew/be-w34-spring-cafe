package com.kakao.cafe.controller;

import com.kakao.cafe.controller.auth.AuthControl;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.CommentDto;
import com.kakao.cafe.service.CommentService;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class CommentController {
    private final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;
    private final UserService userService;

    CommentController(CommentService commentService, UserService userService) {
        this.commentService = Objects.requireNonNull(commentService);
        this.userService = Objects.requireNonNull(userService);
    }

    @PostMapping("/comments")
    public String writeComment(final HttpServletRequest request, @NonNull final CommentDto commentDto) {
        if (checkNotLogin(request)) {
            return getRedirectLoginWithMsg(request);
        }

        commentService.add(commentDto);
        logger.info("New Comment added");
        return "redirect:/articles/" + commentDto.getArticleIdx();
    }

    @PutMapping("/comments")
    public String editArticle(
            final HttpServletRequest request,
            @NonNull final ArticleDto articleDto
    )
    {
//        if (checkNotLogin(request)) {
//            return getRedirectLoginWithMsg(request);
//        }
//
//        String articleOwnerId;
//        try {
//            articleOwnerId = articleService.getDto(articleDto.getIdx())
//                    .getUserId();
//        } catch (NoSuchElementException e) {
//            return "error/404";
//        }
//
//        if (checkNotOwner(request, articleOwnerId)) {
//            return "redirect:/editArticleFailedNoPerm";
//        }
//
//        if (articleService.update(articleDto)) {
//            return "redirect:/articles/" + articleDto.getIdx();
//        }

        return "redirect:/editArticleFailed";
    }

    @DeleteMapping("/comments/{idx}")
    public String deleteArticle(
            final HttpServletRequest request,
            @PathVariable("idx") final long idx
    )
    {
//        if (checkNotLogin(request)) {
//            return getRedirectLoginWithMsg(request);
//        }
//
//        String articleOwnerId;
//        try {
//            articleOwnerId = articleService.getDto(idx)
//                    .getUserId();
//        } catch (NoSuchElementException e) {
//            return "error/404";
//        }
//
//        if (checkNotOwner(request, articleOwnerId)) {
//            return "redirect:/editArticleFailedNoPerm";
//        }
//
//        if (articleService.delete(idx)) {
//            return "redirect:/";
//        }

        return "error/500";
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
