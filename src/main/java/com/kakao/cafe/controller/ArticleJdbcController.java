package com.kakao.cafe.controller;

import com.kakao.cafe.dto.article.WriteArticleDto;
import com.kakao.cafe.service.article.ArticleJdbcService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ArticleJdbcController {
    private ArticleJdbcService articleJdbcService;

    @GetMapping("/articlesdb")
    public String showArticles(Model model) {
        model.addAttribute("articles", this.articleJdbcService.findAllArticles());
        return "article/list";
    }

    @PostMapping("/article/postdb")
    public String postArticle(WriteArticleDto writeArticleDto) {
        this.articleJdbcService.save(writeArticleDto);
        return "redirect:/";
    }

    @GetMapping("/articlesdb/{id}")
    public String showArticle(@PathVariable int id, Model model) {
        model.addAttribute("article", this.articleJdbcService.findArticleById(id));
        return "article/show";
    }


}
