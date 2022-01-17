package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleCreateDTO;
import com.kakao.cafe.article.dto.ArticleListDTO;
import com.kakao.cafe.article.dto.ArticleViewDTO;
import com.kakao.cafe.article.service.ArticleService;
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
    Map<String, String> loginList;

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
    public String showArticleList(Model model, HttpSession session, HttpServletRequest request) {
        System.out.println(getLoggedInUserId(session, request));

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

    //Cookie value에 기반하여 로그인된 아이디를 가져옴
    public String getLoggedInUserId(HttpSession session, HttpServletRequest request){
        if(loginList == null) {
            loginList = (Map<String, String>) session.getAttribute("loginList");
        }

        String userCookieValue = request.getCookies()[0].getValue();

        if(loginList == null){
            return null;
        }

        if(loginList.get(userCookieValue) == null){
            return null;
        }

        return loginList.get(userCookieValue);
    }
}
