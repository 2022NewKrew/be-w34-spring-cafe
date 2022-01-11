package com.kakao.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleList;

@Controller
public class ArticleController {

    private ArticleList articleList = new ArticleList();
    public ArticleController() {}

    @PostMapping("/questions")
    public String createArticle(String title, String content) {
        articleList.addArticle(new Article(title, content, articleList.getArticleListSize()+1));
        return "redirect:/";
    }

    @GetMapping("/")
    public String listArticles(Model model) {
        model.addAttribute("articles", articleList.getArticleList());
        return "index";
    }


    @GetMapping("/articles/{index}")
    public String showDetailedArticle(@PathVariable Integer index, Model model) {
        Article article = articleList.getArticleWithIndex(index);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("content", article.getContent());
        return "qna/show";
    }


}
