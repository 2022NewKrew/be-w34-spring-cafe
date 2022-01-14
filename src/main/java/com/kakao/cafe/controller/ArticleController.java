package com.kakao.cafe.controller;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.ArticleSaveDTO;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/{id}")
    public String findArticleById(@PathVariable String id, Model model){
        Article article = articleService.findArticleById(id);
        model.addAttribute("article", article);
        return "article/view";
    }

    @GetMapping("/post")
    public String articlePostView(){
        return "article/post";
    }

    @PostMapping("/post")
    public String postArticle(@Valid ArticleSaveDTO articleSaveDTO){
        articleService.save(articleSaveDTO);
        return "redirect:/";
    }
}
