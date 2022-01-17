package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public String goQuestion(){
        logger.info("go to Q&A form");
        return "/qna/form";
    }

    @PostMapping("/articles")
    public String createQuestion(Article article){
        articleService.save(article);
        logger.info("article created : {} ", article.getTitle());
        return "redirect:/";
    }

    @GetMapping("/")
    public String viewAllArticles(Model model){
        List<Article> articles = articleService.findAll();
        model.addAttribute("article", articles);
        return "/index";
    }

    @GetMapping("/articles/{index}")
    public String getArticleByIndex(@PathVariable int index, Model model){
        Article article = articleService.findOneById(index);
        model.addAttribute("article",  article);
        model.addAttribute("contents", article.getContents());
        return "/qna/show";
    }
}
