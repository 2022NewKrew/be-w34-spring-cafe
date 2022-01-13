package com.kakao.cafe.web;

import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.web.dto.ArticleCreateRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String getArticleList(Model model) {
        model.addAttribute("articles", articleService.getArticleList());
        return "index";
    }

    @PostMapping("/questions")
    public String postArticle(String writer, String title, String contents) {
        articleService.postArticle(new ArticleCreateRequestDto(writer, title, contents));
        return "redirect:/";
    }

    @GetMapping("/articles/{id}")
    public String getArticleDetail(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.getArticleDetail(id));
        return "qna/show";
    }
}
