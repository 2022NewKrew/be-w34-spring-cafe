package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.ArticlePostDto;
import com.kakao.cafe.article.dto.ArticleRequest;
import com.kakao.cafe.article.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/question")
    public String writeArticle(ArticleRequest articleRequest){
        articleService.writeArticle(articleRequest);

        return "redirect:/";
    }

    @GetMapping("/show/{id}")
    public String getArticleShowPage(@PathVariable String id, Model model){
        ArticlePostDto article = articleService.getArticleById(Long.parseLong(id));
        model.addAttribute("article", article);

        return "/qna/show";
    }
}
