package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.ArticlePostDto;
import com.kakao.cafe.article.dto.ArticleRequest;
import com.kakao.cafe.article.exception.ArticleAuthorMismatchException;
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
        return "/qna/form";
    }

    @PostMapping("/question")
    public String writeArticle(ArticleRequest articleRequest){
        articleService.writeArticle(articleRequest);

        return "redirect:/";
    }

    @GetMapping("/show/{id}")
    public String getArticleShowPage(@PathVariable Long id, Model model){
        ArticlePostDto article = articleService.getArticlePostDtoById(id);
        model.addAttribute("article", article);
        return "/qna/show";
    }

    @GetMapping("/update/{id}")
    public String getUpdateArticlePage(@PathVariable Long id, Model model) {
        if (articleService.isAuthor(id, authService.getLoginUserId())) {
            model.addAttribute("id", id);
            return "/qna/updateForm";
        }
        throw new ArticleAuthorMismatchException();
    }

    @PutMapping("/update/{id}")
    public String updateArticle(@PathVariable Long id, ArticleRequest articleRequest) {
        articleService.updateArticle(id, articleRequest);
        return "redirect:/";
    }
}
