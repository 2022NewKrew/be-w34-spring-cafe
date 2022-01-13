package com.kakao.cafe.controller;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.repository.ArticleJdbcRepository;
import com.kakao.cafe.repository.ArticleMemoryRepository;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleJdbcRepository articleRepository;

    public ArticleController(ArticleJdbcRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @PostMapping("/questions/create")
    public String addArticle(Article article){
        articleRepository.save(article);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showArticles(Model model){
        model.addAttribute("articles", articleRepository.findAllArticles());
        return "index";
    }

    @GetMapping("questions/{id}")
    public String getArticle(@PathVariable Integer id, Model model){
        Article article = articleRepository.findById(id).orElseThrow();
        model.addAttribute("article", article);
        return "qna/show";
    }

}
