package com.kakao.cafe.web;

import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.dto.SampleUserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private Articles articles = new Articles();

    @GetMapping("/articles")
    public String articles(Model model){
        logger.info("article list print");
        model.addAttribute("articles", articles.getArticles());
        return "articleListPage";
    }

    @GetMapping("/articles/{articleID}")
    public String article(Model model, @PathVariable Integer articleID){
        logger.info("article print articleID : {}", articleID);
        model.addAttribute("article", articles.findArticle(articleID));

        return "articlePage";
    }

    @GetMapping("/article/write")
    public String writeArticle(){
        logger.info("writeArticle page");
        return "articleForm";
    }

    @PostMapping("/article/create")
    public String createArticle(SampleArticleForm form){
        logger.info("userCreate print {}" ,form.toString());
        articles.addArticle(form);
        return "redirect:/articles";
    }
}
