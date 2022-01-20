package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.ArticleCreateRequest;
import com.kakao.cafe.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/articles", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createArticle(ArticleCreateRequest request) {
        log.info("start createArticle()");
        articleService.createArticle(request);
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("articles", articleService.list());
        return "/index";
    }

    @GetMapping("/articles/{articleId}")
    public String detail(@PathVariable int articleId, Model model) {
        model.addAttribute("article", articleService.detail(articleId));
        return "/articles/show";
    }

    @GetMapping("/articles/form")
    public String form(HttpServletRequest request, Model model) {
        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        model.addAttribute("name", sessionedUser.getName());
        model.addAttribute("userPk", sessionedUser.getId());
        return "/articles/form";
    }
}
