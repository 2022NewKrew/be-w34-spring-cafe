package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserSignUpDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping("/questions")
    public String postQuestion(ArticleDto article) {
        try {
            articleService.postArticle(article);
        } catch (SQLException e) {
            e.printStackTrace();
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/{index}")
    public String showArticle(@PathVariable int index, Model model) {
        ArticleDto article;
        UserDto writer;

        try {
            article = articleService.findByIndex(index);
            writer = userService.findByName(article.getWriter());
            model.addAttribute("article", article);
            model.addAttribute("writer", writer);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return "redirect:/";
        }

        return "/qna/show";
    }
}
