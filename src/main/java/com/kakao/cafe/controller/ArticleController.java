package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping("/questions")
    public String postQuestion(ArticleDto article) {
        article.setId(articleService.getArticleList().size() + 1);
        articleService.saveArticle(article);
        return "redirect:/";
    }

    @GetMapping("/{index}")
    public String showArticle(@PathVariable int index, Model model) {
        ArticleDto article = articleService.getArticleBy(index);
        UserDto writer = userService.findUserByName(article.getWriter());
        model.addAttribute("article", article);
        model.addAttribute("writer", writer);
        return "/qna/show";
    }
}
