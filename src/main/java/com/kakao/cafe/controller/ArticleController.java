package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.SessionCheck;
import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.dto.ReplyDTO;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.exception.NoChangeException;
import com.kakao.cafe.exception.NoModifyPermissionException;
import com.kakao.cafe.exception.OtherUserReplyExistException;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Resource(name = "articleService")
    private ArticleService articleService;

    @GetMapping
    String posts(Model model) {
        model.addAttribute("articles", articleService.getArticleList());
        return "article/list";
    }

    @PostMapping
    @SessionCheck
    String articles(HttpSession session, @Valid ArticleDTO article, Model model) {
        if (articleService.insertArticle(article) < 1) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, Constants.DEFAULT_ERROR_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }

        log.info("create Article -> Writer : {}, Title : {}", article.getWriterId(), article.getTitle());
        return "redirect:/";
    }

    @GetMapping("/form")
    @SessionCheck
    String form(HttpSession session) {
        return "article/form";
    }

    @GetMapping("/{id}/form")
    @SessionCheck
    String getArticleForm(HttpSession session, @PathVariable long id, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("sessionUser");
        ArticleDTO article = articleService.getArticleById(id);
        articleService.validArticleOwnerShip(article, user);

        model.addAttribute("article", article);
        log.info("get Article(Form) -> UserID : {}, ArticleId : {}", user.getId(), article.getId());

        return "article/updateForm";
    }

    @PutMapping("/{id}/update")
    @SessionCheck
    String updateArticle(HttpSession session, @PathVariable long id, @Valid ArticleDTO article, Model model) {
        UserDTO sessionUser = (UserDTO) session.getAttribute("sessionUser");
        try {
            articleService.updateArticle(sessionUser, id, article);
        } catch (NoModifyPermissionException | NoChangeException exception) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, exception.getMessage());
            return Constants.ERROR_PAGE_NAME;
        }
        log.info("update Article -> ID : {}, Writer : {}, Title : {}", id, article.getWriterId(), article.getTitle());
        return "redirect:/";

    }

    @DeleteMapping("/{id}/delete")
    @SessionCheck
    String deleteArticle(HttpSession session, @PathVariable long id, Model model) {
        UserDTO sessionUser = (UserDTO) session.getAttribute("sessionUser");
        ArticleDTO article = articleService.getArticleById(id);

        try {
            articleService.deleteArticle(id, article, sessionUser);
        } catch (NoModifyPermissionException | NoChangeException | OtherUserReplyExistException exception) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, exception.getMessage());
            return Constants.ERROR_PAGE_NAME;
        }

        log.info("delete Article -> ID : {}, Writer : {}, Title : {}", id, article.getWriterId(), article.getTitle());
        return "redirect:/";
    }

    @GetMapping("/{articleId}")
    @SessionCheck
    String show(HttpSession session, @PathVariable long articleId, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("sessionUser");
        articleService.getArticle(articleId, user, model);
        log.info("get Article -> articleId : {}", articleId);
        return "article/show";
    }

    @PostMapping("/{articleId}/reply/")
    @SessionCheck
    String insertReply(HttpSession session, @PathVariable long articleId, @Valid ReplyDTO reply, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("sessionUser");
        long userId = user.getId();

        try {
            articleService.insertReply(reply, userId);
        } catch (NoChangeException exception) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, exception.getMessage());
            return Constants.ERROR_PAGE_NAME;
        }

        log.info("create Reply -> Article : {}, Writer : {}", articleId, userId);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/{articleId}/reply/{replyId}")
    @SessionCheck
    String deleteReply(HttpSession session, @PathVariable long articleId, @PathVariable long replyId, Model model) {
        UserDTO sessionUser = (UserDTO) session.getAttribute("sessionUser");
        long userId = sessionUser.getId();

        try {
            articleService.deleteReply(userId, replyId);
        } catch (NoChangeException | NoModifyPermissionException exception) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, exception.getMessage());
            return Constants.ERROR_PAGE_NAME;
        }

        log.info("delete Reply -> ID : {}, Article : {}, Writer : {}", replyId, articleId, userId);
        return "redirect:/articles/" + articleId;
    }


}
