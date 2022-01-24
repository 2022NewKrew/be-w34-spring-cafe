package com.kakao.cafe.controller;

import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    public String articles(Model model){
        logger.info("article list print");
        model.addAttribute("articles", articleService.getArticles());
        return "article/articleListPage";
    }

    @GetMapping("/{articleID}")
    public String article(Model model, @PathVariable Long articleID){
        logger.info("article print articleID : {}", articleID);
        model.addAttribute("article", articleService.findArticle(articleID));

        return "article/articlePage";
    }

    @GetMapping("/write")
    public String writeArticle(){
        logger.info("writeArticle page");
        return "article/articleForm";
    }

    @PostMapping("/create")
    public String createArticle(SampleArticleForm form){
        logger.info("userCreate print {}" ,form.toString());
        articleService.addArticle(form);
        return "redirect:/article";
    }
}
