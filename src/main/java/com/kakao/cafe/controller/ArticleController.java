package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @PostMapping("/questions/create")
    public String addArticle(Article article){
        articleRepository.save(article);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showArticles(Model model){
        model.addAttribute("articles", articleRepository.getArticleList());
        return "index";
    }

    @GetMapping("questions/{id}")
    public String getArticle(@PathVariable Integer id, Model model){
        Article article = articleRepository.getArticleList().get(id);
        model.addAttribute("article", article);
        return "qna/show";
    }

}
