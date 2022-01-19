package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.ArticlePostDto;
import com.kakao.cafe.article.dto.ArticleRequest;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final AuthService authService;

    @GetMapping("/form")
    public String getArticleFormPage(){
        if (authService.isLogin()) {
            return "/qna/form";
        }
        return "/user/login";
    }

    @PostMapping("/question")
    public String writeArticle(ArticleRequest articleRequest){
        articleService.writeArticle(articleRequest);

        return "redirect:/";
    }

    @GetMapping("/show/{id}")
    public String getArticleShowPage(@PathVariable String id, Model model){
        if(authService.isLogin()){
            ArticlePostDto article = articleService.getArticleById(Long.parseLong(id));
            model.addAttribute("article", article);
            return "/qna/show";
        }
        return "/user/login";
    }
}
