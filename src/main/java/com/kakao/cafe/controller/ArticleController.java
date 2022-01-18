package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@Controller
public class ArticleController {
    private static final String ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE = "다른사용자가 작성한 글은 수정할 수 없습니다.";

    @Resource(name = "articleService")
    private ArticleService articleService;


    @GetMapping
    String posts(Model model) {
        model.addAttribute("articles", articleService.getArticleList());
        return "index";
    }

    @GetMapping("/articles/form")
    String form(HttpSession session) {
        if (session.getAttribute("sessionUser") == null) {
            return "redirect:/users/login";
        }
        return "article/form";
    }

    @PostMapping("/articles")
    String articles(HttpSession session, @Valid ArticleDTO article, Model model) {
        if (session.getAttribute("sessionUser") == null) {
            return "redirect:/users/login";
        }
        if (articleService.insertArticle(article) < 1) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, Constants.DEFAULT_ERROR_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }

        log.info("create Article -> Writer : {}, Title : {}", article.getWriterId(), article.getTitle());
        return "redirect:/";
    }

    @GetMapping("/articles/{id}/form")
    String getArticleForm(@PathVariable long id, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("sessionUser");
        ArticleDTO article = articleService.getArticleById(id);
        if (user == null) {
            return "redirect:/users/login";
        }
        if (!Objects.equals(article.getWriterId(), user.getId())) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        model.addAttribute("article", article);
        log.info("get Article(Form) -> UserID : {}, ArticleId : {}", user.getId(), article.getId());

        return "article/updateForm";
    }

    @PutMapping("/articles/{id}/update")
    String updateArticle(@PathVariable long id, @Valid ArticleDTO article, Model model, HttpSession session) {
        UserDTO sessionUser = (UserDTO) session.getAttribute("sessionUser");
        ArticleDTO articleInfo = articleService.getArticleById(id);
        if (sessionUser == null) {
            return "redirect:/users/login";
        }
        if (!Objects.equals(articleInfo.getWriterId(), sessionUser.getId())) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        if (articleService.updateArticle(id, article) <= 0) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, Constants.DEFAULT_ERROR_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        log.info("update Article -> ID : {}, Writer : {}, Title : {}", id, article.getWriterId(), article.getTitle());
        return "redirect:/";
    }

    @DeleteMapping("/articles/{id}/delete")
    String deleteArticle(@PathVariable long id, Model model, HttpSession session) {
        UserDTO sessionUser = (UserDTO) session.getAttribute("sessionUser");
        ArticleDTO article = articleService.getArticleById(id);
        if (sessionUser == null) {
            return "redirect:/users/login";
        }
        if (!Objects.equals(article.getWriterId(), sessionUser.getId())) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        if (articleService.deleteArticle(id) <= 0) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, Constants.DEFAULT_ERROR_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        log.info("delete Article -> ID : {}, Writer : {}, Title : {}", id, article.getWriterId(), article.getTitle());
        return "redirect:/";
    }

    @GetMapping("/articles/{articleId}")
    String show(HttpSession session, @PathVariable long articleId, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("sessionUser");
        if (user == null) {
            return "redirect:/users/login";
        }
        articleService.increaseViews(articleId);
        ArticleDTO article = articleService.getArticleById(articleId);
        model.addAttribute("isOwner", Objects.equals(article.getWriterId(), user.getId()));
        model.addAttribute("article", article);
        model.addAttribute("articleId", articleId);
        log.info("get Article -> articleId : {}", articleId);
        return "article/show";
    }

}
