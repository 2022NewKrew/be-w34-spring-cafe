package com.kakao.cafe.controller;


import com.kakao.cafe.dto.article.ArticleReqDto;
import com.kakao.cafe.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public String createPost(@ModelAttribute ArticleReqDto articleReqDto){
        articleService.addPost(articleReqDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getArticleList(Model model){
        model.addAttribute("articles", articleService.findArticles());
        return "index";
    }

    @GetMapping("/articles/form")
    public String getArticleForm(){
        return "article/form";
    }
}
