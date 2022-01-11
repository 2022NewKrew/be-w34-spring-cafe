package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Page;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.UtilClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    ArticleService articleService = new ArticleService();

    @PostMapping("/questions")
    public String createQNA(Article article){
        articleService.join(article);
        return "redirect:/";
    }



    @GetMapping("/")
    public String index(Model model){
        List<Article> articleList = articleService.findArticles();
        List<Page> pageList = UtilClass.makePageList(articleList.size());
        model.addAttribute("articles", articleList);
        model.addAttribute("pageList", pageList);
        return "index";

    }

}
