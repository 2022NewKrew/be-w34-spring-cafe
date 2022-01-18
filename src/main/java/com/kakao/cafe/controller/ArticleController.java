package com.kakao.cafe.controller;

import com.kakao.cafe.exception.NotLoginException;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ErrorService;
import com.kakao.cafe.service.SessionService;
import com.kakao.cafe.vo.Article;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final ErrorService errorService;
    private final SessionService sessionService;

    public ArticleController(ArticleService articleService, ErrorService errorService, SessionService sessionService) {
        this.articleService = articleService;
        this.errorService = errorService;
        this.sessionService = sessionService;
    }

    @PostMapping("/article/create")
    public String questionForm(Article article) {
        articleService.addArticle(article);
        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String getDetails(@PathVariable int index, Model model, HttpSession session) throws NotLoginException {
        User loginUser = sessionService.getLoginUser(session);
        errorService.checkLogin(loginUser);

        Article article = articleService.getArticle(index);
        model.addAttribute("article", article);
        return "/qna/show";
    }

    @GetMapping("/")
    public String getArticles(Model model) {
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("questions/form")
    public String getQuestionForm(Model model, HttpSession session) throws NotLoginException {
        User loginUser = sessionService.getLoginUser(session);
        errorService.checkLogin(loginUser);
        model.addAttribute("userId", loginUser.getUserId());
        return "/qna/form";
    }

}
