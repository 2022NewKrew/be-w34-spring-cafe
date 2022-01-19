package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Articles;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.Constant;
import com.kakao.cafe.util.ErrorMessage;
import com.kakao.cafe.util.UtilClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class ArticleController {
    ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/questions")
    public String createQNA(Article article) {
        log.info(article.toString());
        articleService.join(article);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/list?page=1";
    }

    @GetMapping("/list")
    public String index(int page, Model model) {
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
    public String show(@PathVariable Long index, Model model) {
        Article article = articleService.findOne(index);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("writer", article.getWriter());
        model.addAttribute("content", article.getContent());

        return "/qna/show";
    }

    @GetMapping("/qna/form")
    public String writeArticle(HttpSession session) {
        User user = (User) session.getAttribute(Constant.LOGIN_SESSION);
        if (user == null) {
            return "redirect:/users/login";
        }
        return "/qna/form";
    }

    @PostMapping("/qna/update/{index}")
    public String updateForm(@PathVariable Long index, Model model, HttpSession session) {
        Article article = checkWriter(session, index);
        model.addAttribute("index", index);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("content", article.getContent());
        return "/qna/updateForm";
    }

    @PutMapping("/qna/updateArticle/{index}")
    public String updateArticle(@PathVariable Long index, Article article, HttpSession session) {
        checkWriter(session, index);
        article.setIndex(index);
        articleService.updateArticle(article);
        return "redirect:/articles/" + index;
    }

    @DeleteMapping("/qna/deleteArticle/{index}")
    public String deleteArticle(@PathVariable Long index, HttpSession session) {
        checkWriter(session, index);
        articleService.deleteArticle(index);
        return "redirect:/";
    }

    private Article checkWriter(HttpSession session, Long index) {
        User user = (User) session.getAttribute(Constant.LOGIN_SESSION);
        if (user == null) {
            throw new IllegalStateException(ErrorMessage.UPDATE_NON_LOGIN.getMsg());
        }

        Article article = articleService.findOne(index);

        if (!user.getUserId().equals(article.getWriterId())) {
            throw new IllegalStateException(ErrorMessage.UPDATE_FORBIDDEN.getMsg());
        }

        article.setWriterId(user.getUserId());
        return article;
    }
}
