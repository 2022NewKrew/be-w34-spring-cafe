package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticlePostDto;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ArticleServiceImpl;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;
    private final UserService userService;

    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping("/questions")
    public String postQuestion(ArticlePostDto article) {
        try {
            articleService.postOne(article);
        } catch (SQLException e) {
            logger.error("/articles/questions, failed to create article (article = {})", article, e);
            return "redirect:/";
        } catch (NoSuchElementException e) {
            logger.error("/articles/questions, failed to create article. writer(id = {}) does not exist", article.getWriter(), e);
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/{index}")
    public String showArticle(@PathVariable int index, Model model) {
        ArticleDto article;
        UserProfileDto writer;

        try {
            article = articleService.findById(index);
            writer = userService.findByName(article.getWriter());
            model.addAttribute("article", article);
            model.addAttribute("writer", writer);
        } catch (NoSuchElementException e) {
            logger.error("/articles/index, article id = {}", index, e);
            return "redirect:/";
        }

        return "/qna/show";
    }
}
