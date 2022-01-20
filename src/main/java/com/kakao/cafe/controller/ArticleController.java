package com.kakao.cafe.controller;

import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.dto.CommentDto;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.annotation.Auth;
import com.kakao.cafe.util.annotation.MyArticle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public String articleListView(Model model) {
        List<ArticleDto> articleList = articleService.getArticleList();
        model.addAttribute("articles", articleList);
        return "index";
    }

    @Auth
    @GetMapping("/articles/{index}")
    public String articleView(@PathVariable int index, Model model) {
        ArticleDto article = articleService.filterArticleByIndex(index);
        model.addAttribute("article", article);
        return "qna/show";
    }

    @Auth
    @GetMapping("/articles")
    public String writeArticleView() {
        return "qna/form";
    }

    @Auth
    @PostMapping("/articles")
    public String writeArticle(ArticleDto article, HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("sessionedUser");
        articleService.writeArticle(article, loginUser);
        return "redirect:";
    }

    @MyArticle
    @GetMapping("/articles/{index}/update")
    public String updateArticleView(@PathVariable int index, Model model) {
        ArticleDto article = articleService.filterArticleByIndex(index);
        model.addAttribute("article", article);
        return "qna/updateForm";
    }

    @MyArticle
    @PutMapping("/articles/{index}/update")
    public String updateArticle(@PathVariable int index, ArticleDto article) {
        articleService.updateArticle(index, article);
        return "redirect:";
    }

    @MyArticle
    @DeleteMapping("/articles/{index}/delete")
    public String deleteArticle(@PathVariable int index) {
        articleService.deleteArticle(index);
        return "redirect:/";
    }

    @Auth
    @PostMapping("/articles/{index}/comments")
    public String writerComment(@PathVariable int index, CommentDto comment, HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("sessionedUser");
        articleService.writerComment(index, comment, loginUser);
        return "redirect:";
    }
}
