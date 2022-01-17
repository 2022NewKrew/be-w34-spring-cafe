package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleCreateDTO;
import com.kakao.cafe.article.dto.ArticleListDTO;
import com.kakao.cafe.article.dto.ArticleViewDTO;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.user.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ArticleController {
    ArticleService articleService;

    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    //새로운 질문 생성
    @PostMapping(value = "/qna/create")
    public String createArticle(ArticleCreateDTO articleCreateDTO){
        articleService.articleCreate(articleCreateDTO);
        return "redirect:/";
    }

    //index.html에 노출되는 질문리스트
    @GetMapping(value = {"/", "/index"})
    public String showArticleList(Model model, HttpSession session) {
        String loggedInUserId = null;
        if(session.getAttribute("user") != null) {
            loggedInUserId = ((User) session.getAttribute("user")).getUserId();
        }
        if(loggedInUserId != null){
            model.addAttribute("loggedInUserName", loggedInUserId);
        }

        List<Article> articles = articleService.getAllArticles();
        List<ArticleListDTO> articleListDTO = articles.stream().map(ArticleListDTO::new).collect(Collectors.toList());
        model.addAttribute("articles", articleListDTO);

        return "index";
    }

    //상세 질문글 확인
    @GetMapping(value = "/qnas/{sequence}")
    public String showArticle(@PathVariable("sequence") Long sequence, Model model){
        Article article = articleService.getArticleBySequence(sequence);
        ArticleViewDTO articleViewDTO = new ArticleViewDTO(article);

        model.addAttribute("name", articleViewDTO.getName());
        model.addAttribute("title", articleViewDTO.getTitle());
        model.addAttribute("date", articleViewDTO.getDate());
        model.addAttribute("contents", articleViewDTO.getContents());
        return "/qna/show";
    }
}
