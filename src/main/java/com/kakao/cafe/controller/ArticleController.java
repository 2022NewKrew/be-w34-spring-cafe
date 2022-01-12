package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Articles;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.UtilClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);
    ArticleService articleService = new ArticleService();

    @PostMapping("/questions")
    public String createQNA(Article article){
        articleService.join(article);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/list?page=1";
    }

    @GetMapping("/list")
    public String index(int page, Model model){
        logger.info("{}page list", page);
        Articles articles = new Articles();
        int numOfArticles = articleService.numOfArticles();
        articles.setTotalCount(numOfArticles);
        articles.setPageList(UtilClass.makePageList(numOfArticles));
        articles.setHasPrev(page != 1);
        articles.setHasNext(page != ((numOfArticles - 1) / 10) + 1);
        articles.setPrev(page - 1);
        articles.setNext(page + 1);

        articles.setArticleList(articleService.findSubList(page));

        model.addAttribute("posts", articles);
        return "index";
    }
}
