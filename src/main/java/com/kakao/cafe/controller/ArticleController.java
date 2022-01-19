package com.kakao.cafe.controller;

import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final ArticleService articleService;

    @GetMapping
    public String findArticles(Model model) {
        List<ArticleDto> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        logger.info("GET /articles");
        return "index";
    }

    @GetMapping("{id}")
    public String findArticleOne(@PathVariable int id, Model model) {
        ArticleDto articles = articleService.findById(id);
        model.addAttribute("article", articles);
        logger.info("GET /articles/{}: {}", articles.getId(), articles);
        return "/qna/show";
    }

    @GetMapping("form")
    public String showArticleCreateForm(HttpSession httpSession) {
        Object sessionId = httpSession.getAttribute("sessionId");
        logger.info("GET /articles/form: {}", sessionId);
        return "/qna/form";
    }

    @PostMapping
    public String createArticle(@ModelAttribute ArticleDto articleDto, HttpSession httpSession) {
        Object sessionId = httpSession.getAttribute("sessionId");
        articleService.create(String.valueOf(sessionId), articleDto);
        logger.info("POST /articles: {}", articleDto);
        return "redirect:/";
    }

    @GetMapping("{id}/form")
    public String showArticleUpdateForm(@PathVariable int id, HttpSession httpSession, Model model) {
        Object sessionId = httpSession.getAttribute("sessionId");
        logger.info("GET /articles/{}/form", id);
        model.addAttribute("article", articleService.findByIdForWriter(id, String.valueOf(sessionId)));
        return "/qna/updateForm";
    }

    @PutMapping("{id}")
    public String updateArticle(@PathVariable int id, @ModelAttribute ArticleDto articleDto, HttpSession httpSession) {
        Object sessionId = httpSession.getAttribute("sessionId");
        articleService.update(String.valueOf(sessionId), id, articleDto);
        logger.info("PUT /articles/{} : {}", id, articleDto);
        return "redirect:/";
    }

    @DeleteMapping("{id}")
    public String deleteArticle(@PathVariable int id, HttpSession httpSession) {
        Object sessionId = httpSession.getAttribute("sessionId");
        logger.info("DELETE /articles/{}", sessionId);
        articleService.delete(id, String.valueOf(sessionId));
        return "redirect:/";
    }
}
