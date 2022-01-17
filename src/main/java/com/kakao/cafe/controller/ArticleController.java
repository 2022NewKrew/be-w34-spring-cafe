package com.kakao.cafe.controller;

import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.service.ArticleService;
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

    @GetMapping("/articles/{index}")
    public String articleView(@PathVariable int index, Model model) {
        ArticleDto article = articleService.filterArticleByIndex(index);
        model.addAttribute("article", article);
        return "qna/show";
    }

    @GetMapping("/articles")
    public String writeArticleView(HttpSession session, Model model) {
        UserDto loginUser = (UserDto) session.getAttribute("sessionedUser");
        if (loginUser != null) {
            return "qna/form";
        }
        return "redirect:/users/login";
    }

    @PostMapping("/articles")
    public String writeArticle(ArticleDto article, HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("sessionedUser");
        articleService.writeArticle(article, loginUser);
        return "redirect:";
    }

    @GetMapping("/articles/{index}/update")
    public String updateArticleView(@PathVariable int index, Model model) {
        ArticleDto article = articleService.filterArticleByIndex(index);
        model.addAttribute("article", article);
        return "qna/updateForm";
    }

    @PutMapping("/articles/{index}/update")
    public String updateArticle(@PathVariable int index, ArticleDto article) {
        articleService.updateArticle(index, article);
        return "redirect:";
    }

    @DeleteMapping("/articles/{index}/delete")
    public String deleteArticle(@PathVariable int index) {
        articleService.deleteArticle(index);
        return "redirect:/";
    }
}
