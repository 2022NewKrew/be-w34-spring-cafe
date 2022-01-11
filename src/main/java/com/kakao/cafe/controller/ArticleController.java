package com.kakao.cafe.controller;

import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles/post")
    public String form() {
        return "./qna/form";
    }

    @PostMapping("/articles/question")
    public String question(@Valid ArticleDto articleDto) {
        logger.info("게시글 정보 : {}", articleDto);
        articleService.addArticle(articleDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String questionList(Model model) {
        List<ArticleDto> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "./qna/list";
    }

    @GetMapping("/articles/{id}")
    public String showArticle(@PathVariable int id, Model model) {
        ArticleDto articleDto = articleService.getArticle(id);
        model.addAttribute("article", articleDto);
        return "./qna/show";
    }

}
