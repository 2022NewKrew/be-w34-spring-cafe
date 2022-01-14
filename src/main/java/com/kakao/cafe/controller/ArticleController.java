package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Articles;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.UtilClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ArticleController {
    ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

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
        log.info("{}page list", page);
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

    @GetMapping("/articles/{index}")
    public String show(@PathVariable Long index, Model model){
        Article article = articleService.findOne(index);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("writer", article.getWriter());
        model.addAttribute("content", article.getContent());

        return "/qna/show";
    }
}
