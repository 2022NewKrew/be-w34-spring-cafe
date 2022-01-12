package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/articles")
    public String goQuestion(){
        logger.info("go to Q&A form");
        return "/qna/form";
    }

    @PostMapping("/articles")
    public String createQuestion(Article article){
        ArticleList.addArticle(article);
        logger.info("article created : {} ", article.getTitle());
        return "redirect:/";
    }

    @GetMapping("/")
    public String viewAllArticles(Model model){
        model.addAttribute("article", ArticleList.getArticleList());
        return "/index";
    }

    @GetMapping("/articles/{index}")
    public String getArticleByIndex(@PathVariable int index, Model model){
        model.addAttribute("article", ArticleList.getArticleByIndex(index));
        model.addAttribute("contents",ArticleList.getArticleByIndex(index).getContents());
        return "/qna/show";
    }
}
