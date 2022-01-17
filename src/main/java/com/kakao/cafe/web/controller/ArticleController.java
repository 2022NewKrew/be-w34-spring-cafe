package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.ArticleCreateDTO;
import com.kakao.cafe.web.dto.ArticleUpdateDTO;
import com.kakao.cafe.web.dto.UserCreateDTO;
import com.kakao.cafe.web.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final Logger logger;

    ArticleController(ArticleService articleService) {
        this.articleService = articleService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("articleList", articleService.getArticleList());
        return "index";
    }

    @GetMapping("/articles/form")
    public String getQnaForm(HttpSession session) {
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return "redirect:/users/login";
        }
        return "qna/form";
    }

    @PostMapping("/articles/form")
    public String createArticle(HttpSession session, String title, String contents) {
        User user = (User)session.getAttribute("sessionedUser");
        ArticleCreateDTO articleCreateDTO = new ArticleCreateDTO(user.getUserId(), title, contents);
        articleService.writeArticle(articleCreateDTO);
        return "redirect:/";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(Model model, HttpSession session, @PathVariable long id) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("writer", article.getWriter());
        model.addAttribute("title", article.getTitle());
        model.addAttribute("contents", article.getContents());
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return "redirect:/users/login";
        }
        User user = (User)value;
        if (user.getUserId().equals(article.getWriter())) {
            model.addAttribute("modifiable", true);
        }
        return "qna/show";
    }

    @GetMapping("/articles/{id}/form")
    public String getUpdateArticleForm(Model model, HttpSession session, @PathVariable long id) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("contents", article.getContents());
        return "qna/update-form";
    }

    @PutMapping("/articles/{id}/form")
    public String updateArticle(Model model, HttpSession session, @PathVariable long id, String title, String contents) {
        Article article = articleService.getArticleById(id);
        ArticleUpdateDTO articleUpdateDTO = new ArticleUpdateDTO(article.getId(), article.getWriter(), title, contents, article.getModifiedTime());
        articleService.updateArticle(articleUpdateDTO);
        return "redirect:/";
    }

    @DeleteMapping("/articles/{id}")
    public String deleteArticle(HttpSession session, @PathVariable long id) {
        Article article = articleService.getArticleById(id);
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return "redirect:/users/login";
        }
        User user = (User)value;
        if (!user.getUserId().equals(article.getWriter())) {
            return "redirect:/users/login";
        }
        articleService.deleteArticleById(id);
        return "redirect:/";
    }
}
