package com.kakao.cafe.controller;

import com.kakao.cafe.exception.user.NotAllowedUserException;
import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.annotation.Auth;
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

    @GetMapping("/articles/{index}/update")
    public String updateArticleView(@PathVariable int index, HttpSession session, Model model) {
        UserDto loginUser = (UserDto) session.getAttribute("sessionedUser");
        ArticleDto article = articleService.filterArticleByIndex(index);
        if (loginUser == null || !loginUser.getUserId().equals(article.getWriter().getUserId())) {
            throw new NotAllowedUserException();
        }
        model.addAttribute("article", article);
        return "qna/updateForm";
    }

    @PutMapping("/articles/{index}/update")
    public String updateArticle(@PathVariable int index, ArticleDto article, HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("sessionedUser");
        ArticleDto oldArticle = articleService.filterArticleByIndex(index);
        if (loginUser == null || !loginUser.getUserId().equals(oldArticle.getWriter().getUserId())) {
            throw new NotAllowedUserException();
        }
        articleService.updateArticle(index, article);
        return "redirect:";
    }

    @DeleteMapping("/articles/{index}/delete")
    public String deleteArticle(@PathVariable int index, HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("sessionedUser");
        ArticleDto article = articleService.filterArticleByIndex(index);
        if (loginUser == null || !loginUser.getUserId().equals(article.getWriter().getUserId())) {
            throw new NotAllowedUserException();
        }
        articleService.deleteArticle(index);
        return "redirect:/";
    }
}
