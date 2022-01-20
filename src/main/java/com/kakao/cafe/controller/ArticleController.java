package com.kakao.cafe.controller;

import com.kakao.cafe.dto.article.ArticleCreationDTO;
import com.kakao.cafe.dto.article.ArticleDTO;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.SessionIdRequired;
import com.kakao.cafe.util.Url;
import com.kakao.cafe.util.View;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping("/articles")
    public String getAllArticle(Model model) {
        var articleList = articleService.getAllArticles().stream().map(this::setAuthorNickname).collect(Collectors.toList());
        model.addAttribute("articleList", articleList);
        model.addAttribute("articleListSize", articleList.size());
        return View.INDEX;
    }

    @SessionIdRequired
    @GetMapping("/articles/form")
    public String getArticleForm(RedirectAttributes attr) {
        return View.ARTICLES_FORM;
    }

    @SessionIdRequired
    @PostMapping("/articles")
    public String createArticle(ArticleCreationDTO dto, HttpSession session, RedirectAttributes attr) {
        long userId = (long) session.getAttribute("sessionedUserId");
        dto.setUserId(userId);
        long id = articleService.post(dto);
        return "redirect:/articles/" + id;
    }

    @SessionIdRequired
    @GetMapping("/articles/{id}")
    public String getArticlePage(@PathVariable long id, HttpSession session, Model model, RedirectAttributes attr) {
        try {
            var article = setAuthorNickname(articleService.getById(id));
            model.addAttribute("article", article);
            addAtributesIfUidEqualToArticleUid(session, article, model);
        } catch (IllegalArgumentException e) {
            handleException(e, attr);
            return "redirect:" + Url.ARTICLES;
        }

        return View.ARTICLES_SHOW;
    }

    @SessionIdRequired
    @PostMapping("/articles/{id}")
    public String updateArticle(@PathVariable long id, HttpSession session, ArticleCreationDTO dto, RedirectAttributes attr) {
        try {
            long uid = (long) session.getAttribute("sessionedUserId");
            articleService.update(id, uid, dto);
        } catch (Exception e) {
            handleException(e, attr);
            return "redirect:" + Url.ARTICLES;
        }

        return "redirect:/articles/" + id;
    }

    @SessionIdRequired
    @GetMapping("/articles/form/{id}")
    public String getUpdateForm(@PathVariable long id, RedirectAttributes attr) {
        try {
            var article = setAuthorNickname(articleService.getById(id));
            attr.addFlashAttribute("article", article);
        } catch (Exception e) {
            handleException(e, attr);
            return "redirect:" + Url.ARTICLES;
        }
        return "redirect:" + Url.ARTICLES_FORM;
    }

    @SessionIdRequired
    @DeleteMapping("/articles/{id}")
    public String deleteArticle(@PathVariable long id, HttpSession session, RedirectAttributes attr) {
        try {
            long sessionUid = (long) session.getAttribute("sessionedUserId");
            articleService.delete(id, sessionUid);
        } catch (Exception e) {
            handleException(e, attr);
            return "redirect:/articles/" + id;
        }

        return "redirect:" + Url.ARTICLES;
    }

    private ArticleDTO setAuthorNickname(ArticleDTO article) {
        long authorId = article.getUserId();
        article.setAuthor(userService.getNicknameById(authorId));

        return article;
    }

    private void handleException(Exception e, RedirectAttributes attr) {
        log.error(e.getMessage(), e);
        attr.addFlashAttribute("errorMsg", e.getMessage());
    }

    private void addAtributesIfUidEqualToArticleUid(HttpSession session, ArticleDTO article, Model model) {
        long uid = (long) session.getAttribute("sessionedUserId");
        if (uid == article.getUserId()) {
            model.addAttribute("canEdit", true);
        }
    }
}
