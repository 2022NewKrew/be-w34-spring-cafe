package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.dto.ArticleDTO;
import com.kakao.cafe.web.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final Logger logger;

    ArticleController(JdbcTemplate jdbcTemplate) {
        this.articleService = new ArticleService(jdbcTemplate);
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("articleList", articleService.getArticleList());
        return "index";
    }

    @GetMapping("/qna/form")
    public String getQnaForm() {
        return "qna/form";
    }

    @PostMapping("/qna/form")
    public String createArticle(String writer, String title, String contents) {
        ArticleDTO articleDTO = new ArticleDTO(writer, title, contents);
        articleService.writeArticle(articleDTO);
        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String getArticle(Model model, @PathVariable int index) {
        Article article = articleService.getArticleById(index);
        model.addAttribute("writer", article.getWriter());
        model.addAttribute("contents", article.getContents());
        return "qna/show";
    }
}
