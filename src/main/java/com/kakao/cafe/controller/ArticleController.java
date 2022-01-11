package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController {

    @PostMapping(value = "/qna/create")
    public String createArticle(Article article, Model model){
        return "redirect:/index.html";
    }
}
