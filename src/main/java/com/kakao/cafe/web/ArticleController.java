package com.kakao.cafe.web;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.service.article.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @GetMapping("article/form.html")
    public String articlePage() {
        return "article/form";
    }

    @PostMapping("article/create")
    public String articleWrite(Article article) {
        logger.info("article: {}", article);
        ArticleService.postArticle(article);
        return "redirect:/";
    }

    @GetMapping("/")
    public String articleMain(Model model) {
        List<Article> articleList = ArticleService.getArticleList().getList();
        model.addAttribute("articles", articleList);
        model.addAttribute("size", articleList.size());
        return "index";
    }

    @GetMapping("article/{articleIndex}")
    public String viewUserProfile(@PathVariable String articleIndex, Model model) {
        logger.info("artile Detail:{}", model.addAttribute("article", ArticleService.getArticleList().findById(articleIndex)));
        return "/article/show";
    }

}
