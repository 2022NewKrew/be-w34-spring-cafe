package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleRegistrationDto;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String articleList(Model model) {
        List<Article> articleList = articleService.getArticles();
        model.addAttribute("articles", articleList);
        return "index";
    }


    @PostMapping
    public String article(@Valid ArticleRegistrationDto articleDto) {
        articleService.write(articleDto);
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String article(@PathVariable Integer id, Model model) {
        Article article = articleService.findByArticleId(id);
        model.addAttribute("article", article);
        return "post/show";
    }

}
