package com.kakao.cafe.controller;


import com.kakao.cafe.auth.LoginCheck;
import com.kakao.cafe.dto.article.ArticleReqDto;
import com.kakao.cafe.dto.user.SessionUser;
import com.kakao.cafe.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public String createPost(@ModelAttribute ArticleReqDto articleReqDto){
        articleService.addArticle(articleReqDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showArticleList(Model model){
        model.addAttribute("articles", articleService.findArticles());
        return "index";
    }

    @GetMapping("/articles/form")
    public String showArticleForm(@LoginCheck SessionUser sessionUser){
        return "article/form";
    }

    @GetMapping("/articles/{articleId}")
    public String showArticle(@PathVariable Long articleId, Model model, @LoginCheck SessionUser sessionUser){
        model.addAttribute("article", articleService.findArticleById(articleId));
        return "article/show";
    }
}
