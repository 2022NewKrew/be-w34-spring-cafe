package com.kakao.cafe.web.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleApiController {
    private final ArticleService articleService;

    Logger logger = LoggerFactory.getLogger(ArticleApiController.class);

    private ArticleApiController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    String getArticles(Model model) {
        List<Article> articleList = articleService.getArticles();
        model.addAttribute("articles", articleList);
        logger.info("Article API: 글 목록");
        return "index";
    }

    @GetMapping("/articles/form")
    String getForm(HttpSession session) {
        Users user = (Users) session.getAttribute("sessionedUser");
        if (user == null)
            return "redirect:/users/login";
        return "articles/form";
    }

    @GetMapping("/articles/{id}")
    String findById(@PathVariable int id, Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("sessionedUser");
        if (user == null)
            return "redirect:/users/login";
        Article article = articleService.getByArticleId(id);
        model.addAttribute("article", article);
        return "articles/show";
    }

    @GetMapping("/articles/{id}/update")
    String getUpdateForm(@PathVariable int id, Model model, HttpSession session) {
        Article article = articleService.getByArticleId(id);
        Users user = (Users) session.getAttribute("sessionedUser");
        if (user == null)
            return "redirect:/users/login";
        if (article.getAuthor().getId() != user.getId()) {
            logger.info("Article API: 허용되지 않은 UPDATE 메소드 접근");
            throw new IllegalArgumentException("글쓴이가 일치하지 않습니다.");
        }
        model.addAttribute("article", article);
        return "articles/updateForm";
    }

    @PutMapping("/articles/{id}")
    String updateArticle(@PathVariable int id, Article article, HttpSession session) {
        Users user = (Users) session.getAttribute("sessionedUser");
        if (user == null)
            return "redirect:/users/login";
        if (articleService.getByArticleId(id).getAuthor().getId() != user.getId()) {
            logger.info("Article API: 허용되지 않은 UPDATE 메소드 접근");
            throw new IllegalArgumentException("글쓴이가 일치하지 않습니다.");
        }
        articleService.updateArticle(id, article);
        logger.info("Article API: 글 수정");
        return "redirect:/articles/{id}";
    }

    @DeleteMapping("/articles/{id}")
    String deleteArticle(@PathVariable int id, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("sessionedUser");
        if (articleService.getByArticleId(id).getAuthor().getId() != currentUser.getId()) {
            logger.info("Article API: 허용되지 않은 DELETE 메소드 접근");
            throw new IllegalArgumentException("글쓴이가 일치하지 않습니다.");
        }
        articleService.deleteArticle(id, currentUser.getId());
        logger.info("Article API: 글 삭제");
        return "redirect:/";
    }

    @PostMapping("/articles")
    String createArticle(Article article, HttpSession session) {
        Users user = (Users) session.getAttribute("sessionedUser");
        if (user == null)
            return "redirect:/users/login";
        article.setAuthor(user);
        articleService.addArticle(article);
        logger.info("Article API: 글 생성");
        return "redirect:/";
    }
}
