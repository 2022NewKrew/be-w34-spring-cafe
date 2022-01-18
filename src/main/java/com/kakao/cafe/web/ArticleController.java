package com.kakao.cafe.web;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.dto.article.ArticleContents;
import com.kakao.cafe.dto.article.ArticleCreateCommand;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String listArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "index";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model, HttpSession session) {
        User user = (User)session.getAttribute("sessionedUser");
        if (userNotExists(user)) {
            return "redirect:/users/login";
        }
        ArticleContents articleContents = articleService.getArticle(id);
        model.addAttribute("article", articleContents);
        return "qna/show";
    }

    @GetMapping("/articles/form")
    public String writeForm(Model model, HttpSession session) {
        User user = (User)session.getAttribute("sessionedUser");
        if (userNotExists(user)) {
            return "redirect:/users/login";
        }

        model.addAttribute("writer", user.getName());
        return "qna/form";
    }

    @GetMapping("/articles/{articleId}/form")
    public String updateForm(@PathVariable long articleId, Model model,
                             @SessionAttribute("sessionedUser") User user) {
        ArticleContents articleContents = articleService.getArticle(articleId);
        if (userNoPermission(user, articleContents)) {
            return "/noPermission";
        }

        model.addAttribute("articleContents", articleContents);
        return "qna/updateForm";
    }

    @PostMapping("/questions")
    public String addArticle(String writer, String title, String contents) {
        articleService.createArticle(new ArticleCreateCommand(writer, title, contents));
        return "redirect:/";
    }

    @PutMapping("/questions/{articleId}/update")
    public String updateArticle(@PathVariable long articleId, String writer, String title, String contents) {
        articleService.modifyArticle(articleId, new ArticleCreateCommand(writer, title, contents));
        return "redirect:/articles/{articleId}";
    }

    @DeleteMapping("questions/{articleId}")
    public String deleteArticle(@PathVariable long articleId, @SessionAttribute("sessionedUser") User user) {
        ArticleContents articleContents = articleService.getArticle(articleId);
        if (userNoPermission(user, articleContents)) {
            return "/noPermission";
        }
        articleService.deleteArticle(articleId);
        return "redirect:/";
    }

    private boolean userNotExists(User user) {
        return user == null;
    }

    private boolean userNoPermission(User user, ArticleContents articleContents) {
        return !user.getName().equals(articleContents.getWriter());
    }
}
