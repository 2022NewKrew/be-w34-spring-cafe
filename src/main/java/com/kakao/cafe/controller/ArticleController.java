package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDetailDto;
import com.kakao.cafe.dto.ArticleListDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.view.ArticleView;
import com.kakao.cafe.view.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    private static final ArticleService articleService = new ArticleService();
    private static final ArticleView articleView = new ArticleView();


    @GetMapping("/index")
    public String getIndex(Model model) {
        List<ArticleListDto> articleList = articleService.getArticleList();
        articleView.getIndexView(model, articleList);
        return "index";
    }

    @GetMapping("/")
    public String getRoot() {
        return "redirect:/index";
    }

    @GetMapping("/article/{id}")
    public String getArticleId(Model model, @PathVariable("id") Long id) {
        ArticleDetailDto articleDetailDto = articleService.getArticleDetailDto(id);
        articleView.getArticleIdView(model, articleDetailDto);
        return "qna/show";
    }

    @GetMapping("/questions")
    public String getQuestions(Model model) {
        articleView.getQuestionsView(model);
        return "qna/form";
    }

    @PostMapping("/questions")
    public String postQuestions(Model model, Article article) {
        articleService.createArticle(article);

        return "redirect:/index";
    }

}
