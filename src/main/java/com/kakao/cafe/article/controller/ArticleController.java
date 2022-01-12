package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleCreateDTO;
import com.kakao.cafe.article.dto.ArticleListDTO;
import com.kakao.cafe.article.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ArticleController {
    ArticleService articleService = new ArticleService();

    //새로운 질문 생성
    @PostMapping(value = "/qna/create")
    public String createArticle(ArticleCreateDTO articleCreateDTO){
        articleService.articleCreate(articleCreateDTO);
        return "redirect:/";
    }

    //index.html에 노출되는 질문리스트
    @GetMapping(value = {"/", "/index"})
    public String showArticleList(Model model) {
        List<Article> articles = articleService.getAllArticles();
        List<ArticleListDTO> articleListDTO = articles.stream().map(ArticleListDTO::new).collect(Collectors.toList());
        model.addAttribute("articles", articleListDTO);

        return "index";
    }

    //상세 질문글 확인
    @GetMapping(value = "/qnas/{sequence}")
    public String showArticle(@PathVariable("sequence") Long sequence, Model model){
        Article article = articleService.getArticle(sequence - 1);
        model.addAttribute("name", article.getName());
        model.addAttribute("title", article.getTitle());
        model.addAttribute("date", article.getDate());
        model.addAttribute("contents", article.getContents());
        return "/qna/show";
    }
}
