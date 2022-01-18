package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(path = "/")
    public String home() {
        return "redirect:/articles";
    }

    @GetMapping("/articles")
    public String getAllArticles(Model model) {
        model.addAttribute("articles", articleService.findArticles());
        return "index";
    }

    @GetMapping("/articles/{articleId}")
    public String getOneArticle(HttpSession session, Model model, @PathVariable long articleId) {
        Optional<Article> article = articleService.findOneById(articleId);
        if (article.isEmpty()) {
            return "redirect:/";
        }

        User user = (User) session.getAttribute("sessionedUser");
        if (user == null || !article.get().getWriter().equals(user.getUserId())) {
            return "redirect:/";
        }

        model.addAttribute("articleInfo", article.get());
        return "qna/show";
    }

    @GetMapping("/articles/{articleId}/form")
    public String updateArticleForm(HttpSession session, Model model, @PathVariable long articleId) {
        Optional<Article> article = articleService.findOneById(articleId);
        if (article.isEmpty()) {
            return "redirect:/";
        }

        User user = (User) session.getAttribute("sessionedUser");
        if (user == null || !article.get().getWriter().equals(user.getUserId())) {
            return "redirect:/";
        }

        model.addAttribute("article", article.get());
        return "qna/updateForm";
    }

    @PostMapping("/articles")
    public String createArticle(@ModelAttribute("article") Article article) {
        String contents = article.getContents().replace("\r\n", "<br>");
        article.setContents(contents);
        article.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        article.setUpdatedTime(Timestamp.valueOf(LocalDateTime.now()));
        articleService.addArticle(article);

        return "redirect:/articles/" + article.getId();
    }

    @PatchMapping("/articles")
    public String updateArticle(long id, String title, String contents, String writer) {
        articleService.update(id, title, contents, writer);
        return "redirect:/articles/" + id;
    }

    @DeleteMapping("/articles/{articleId}")
    public String deleteArticle(@PathVariable long articleId, String sessionUser, String articleUser) {
        if (sessionUser.equals(articleUser)) {
            articleService.delete(articleId);
        }

        return "redirect:/";
    }
}
