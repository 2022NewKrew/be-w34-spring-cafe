package com.kakao.cafe.controller;

import com.kakao.cafe.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class ArticleController {

    List<Article> articleList = new ArrayList<>();

    @GetMapping()
    public String articleListView(Model model) {
        model.addAttribute("articles", articleList);
        return "index";
    }

    @GetMapping("/articles")
    public String View() {
        return "qna/form";
    }

    @PostMapping("/articles")
    public String writeArticle(Article article) {
        articleList.add(article);
        return "redirect:";
    }
}
