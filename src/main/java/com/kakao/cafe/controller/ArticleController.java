package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDetailDto;
import com.kakao.cafe.dto.ArticleListDto;
import com.kakao.cafe.dto.ArticlePostDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.view.ArticleView;
import com.kakao.cafe.view.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public String getArticleId(Model model, @PathVariable("id") Long id, HttpSession session) {
        ArticleDetailDto articleDetailDto = articleService.getArticleDetailDto(id);
        Boolean isSameUser = articleService.checkSameUser(articleDetailDto, session);
        articleView.getArticleIdView(model, articleDetailDto, isSameUser);
        return "qna/show";
    }

    @GetMapping("/questions")
    public String getQuestions(Model model) {
        articleView.getQuestionsView(model);
        return "qna/form";
    }

    @PostMapping("/questions")
    public String postQuestions(Model model, ArticlePostDto articlePostDto, HttpSession session) {
        Article article = articleService.getArticle(articlePostDto, session);
        articleService.createArticle(article);

        return "redirect:/index";
    }

    @GetMapping("/article/{id}/form")
    public String getArticleIdForm(Model model, @PathVariable("id") Long id, HttpSession session) {
        ArticleDetailDto articleDetailDto = articleService.getArticleDetailDto(id);
        Boolean isSameUser = articleService.checkSameUser(articleDetailDto, session);
        if (!isSameUser) {
            return "redirect:/article/error";
        }
        articleView.getArticleIdFormView(model, articleDetailDto);
        return "qna/form_change";
    }

    @PutMapping("/article/{id}/form")
    public String putArticleIdForm(Model model, @PathVariable("id") Long id, HttpSession session, ArticlePostDto articlePostDto) {
        ArticleDetailDto articleDetailDto = articleService.getArticleDetailDto(id);
        Boolean isSameUser = articleService.checkSameUser(articleDetailDto, session);
        if (!isSameUser) {
            return "redirect:/article/error";
        }
        articleService.updateArticle(id, articlePostDto);
        return "redirect:/index";
    }

    @DeleteMapping("/article/{id}")
    public String deleteArticleIdForm(Model model, @PathVariable("id") Long id, HttpSession session) {
        ArticleDetailDto articleDetailDto = articleService.getArticleDetailDto(id);
        Boolean isSameUser = articleService.checkSameUser(articleDetailDto, session);
        if (!isSameUser) {
            return "redirect:/article/error";
        }
        articleService.deleteArticle(id);
        return "redirect:/index";
    }

    @GetMapping("/article/error")
    public String getArticleError(Model model) {
        articleView.getQuestionsView(model);
        return "qna/form";
    }
}
