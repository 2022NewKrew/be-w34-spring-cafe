package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.model.ArticleDTO;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.article.service.ArticleServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class ArticleController {

    static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private ArticleService articleService = new ArticleServiceImp();

    @GetMapping("/")
    public String getArticles(Model model){
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles",articles);


        logger.info("Class : ArticleController, Method : getArticles, Articles : {}", articles);
        return "/index";
    }

    @PostMapping("/questions")
    public String createArticle(ArticleDTO articleDTO){
        logger.info("Class : ArticleController, Method : createArticle, Article : {}", articleService.addArticle(articleDTO));
        return "redirect:/";
    }

    @GetMapping("/articles/{articleId}")
    public String getArticle(@PathVariable String articleId, Model model){
        logger.info("Class : ArticleController, Method : createArticle, Article Index : {}", articleId);
        model.addAttribute("article", articleService.readArticle(Integer.parseInt(articleId)));

        return "/board/show";
    }
}
