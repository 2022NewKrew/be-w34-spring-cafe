package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticlePostDto;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/form")
    public String mainPage(Model model, HttpSession session) {
        String sessionUserId;

        try {
            sessionUserId = userService.getUserIdFromSession(session);
        } catch (NoSuchElementException e) { return "redirect:/"; }

        model.addAttribute("writer", sessionUserId);

        return "/qna/form";
    }

    @PostMapping("/questions")
    public String postQuestion(ArticlePostDto article) {
        try {
            articleService.postOne(article);
        } catch (SQLException e) {
            logger.error("/articles/questions, failed to create article (article = {})", article, e);
            return "redirect:/";
        } catch (NoSuchElementException e) {
            logger.info("/articles/questions, failed to create article. writer(id = {}) does not exist", article.getWriter(), e);
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showArticle(@PathVariable int id, Model model) {
        ArticleDto article;
        UserProfileDto writer;

        try {
            article = articleService.findById(id);
            writer = userService.findByName(article.getWriter());
            model.addAttribute("article", article);
            model.addAttribute("writer", writer);
        } catch (NoSuchElementException e) {
            logger.error("/articles/index, article id = {}", id, e);
            return "redirect:/";
        }

        return "/qna/show";
    }

    @PutMapping("/{id}")
    public String modifyArticle(@PathVariable int id) {
        return "/";
    }

    @GetMapping("/{id}/form")
    public String modifyForm(@PathVariable int id, Model model, HttpSession session) {
        ArticleDto articleDto = articleService.findById(id);
        String writerName = articleDto.getWriter();
        UserProfileDto writer = userService.findByName(writerName);

        if (!userService.checkSessionUser(writer.getUserId(), session)) { // 다른 작성자의 글 수정 불가
            return "redirect:/";
        }

        model.addAttribute("writer", writer.getUserId());

        return "/qna/form";
    }

}
