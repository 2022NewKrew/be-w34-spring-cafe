package com.kakao.cafe.Controller;


import com.kakao.cafe.Domain.Article;
import com.kakao.cafe.Domain.Comment;
import com.kakao.cafe.Domain.User;
import com.kakao.cafe.Service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String home() {
        logger.info("GET / : call GET /articles (home)");
        return "redirect:/articles";
    }

    @PostMapping("/articles")
    public String post(Article article, HttpSession session, Model model) {
        try {
            Object sessionedUser = session.getAttribute("sessionedUser");
            articleService.post(article, sessionedUser);
            logger.info("{}(title) was posted", article.getTitle());

            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error/page";
        }
    }

    @GetMapping("/articles")
    public String getArticles(Model model) {
        logger.info("GET /articles : Get all articles");

        List<Article> findArticles = articleService.findArticles();

        model.addAttribute("articles", findArticles);
        model.addAttribute("articlesCount", findArticles.size());

        return "index";
    }

    @GetMapping("/articles/{articleId}")
    public String getArticle(@PathVariable("articleId") Long articleId,
                             Model model) {
        logger.info("GET /article/{} : Get content of article({})", articleId, articleId);

        Optional<Article> findArticle = articleService.findOne(articleId);
        List<Comment> findComments = articleService.findComments(articleId);
        System.out.println(findComments);

        model.addAttribute("article", findArticle.get());
        model.addAttribute("comments", findComments);
        model.addAttribute("commentsCount", findComments.size());
        return "post/show";
    }


    @GetMapping("/edit-article/{articleId}")
    public String getEditArticle(@PathVariable("articleId") Long articleId,
                                 HttpSession session, Model model) throws IllegalAccessException {
        logger.info("GET /edit-article : Load article edit form");
        try {
            User sessionedUser = (User) session.getAttribute("sessionedUser");
            articleService.checkAuthorMatch(articleId, sessionedUser);
            Optional<Article> findArticle = articleService.findOne(articleId);

            model.addAttribute("article", findArticle.get());
            return "post/edit_post";
        } catch (IllegalAccessException e) {
            model.addAttribute("error", e.getMessage());
            return "error/page";
        }
    }

    @PutMapping("/articles/{articleId}")
    public String putEditArticle(@PathVariable("articleId") Long articleId,
                                 Article article, Model model) {
        logger.info("PUT /articles/{} : Edit article({})", articleId, articleId);
        try {
            articleService.edit(articleId, article);
            logger.info("Article({}) was editted", article.getTitle());

            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error/page";
        }
    }

    @DeleteMapping("/articles/{articleId}")
    public String deleteArticle(@PathVariable("articleId") Long articleId,
                                Model model, HttpSession session) {
        logger.info("DELETE /articles/{} : Delete article({})", articleId, articleId);
        try {
            User sessionedUser = (User) session.getAttribute("sessionedUser");
            articleService.checkAuthorMatch(articleId, sessionedUser);
            articleService.delete(articleId);
            logger.info("Article({}) was deleted", articleId);

            return "redirect:/";
        } catch (IllegalAccessException e) {
            return "redirect:/login-form";
        }
    }
}
