package com.kakao.cafe.interfaces.article;

import com.kakao.cafe.application.ArticleService;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public ModelAndView readAllArticles(ModelAndView modelAndView) {
        List<Article> articles = articleService.readAll();
        modelAndView.addObject("articles", articles);
        modelAndView.setViewName("index");

        return modelAndView;
    }


    @PostMapping("/questions")
    public ModelAndView createArticle(ArticleVo articleVo, ModelAndView modelAndView) {
        articleService.write(articleVo);
        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }

}
