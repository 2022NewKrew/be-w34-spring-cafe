package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.dto.ArticleForm;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleService;
import com.kakao.cafe.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 홈 화면 (질문 리스트 페이지)
    @GetMapping("")
    public String home(Model model){
        List<Article> articles = articleService.getAllArticle();
        model.addAttribute("articles", articles);
        return "index";
    }

    // article(질문하기) 등록
    @PostMapping("")
    public String registerArticle(@Valid ArticleForm articleFrom, @SessionAttribute("sessionedUser") User user){
        logger.info("POST /articles");
        Article article = Article.of(articleFrom);

        article.setWriterUserId(user.getUserId());

        articleService.register(article);
        return "redirect:/articles";
    }

    // article 자세히 보기
    @GetMapping("/{id}")
    public String showArticle(@PathVariable Long id, Model model){
        logger.info("GET /{id}");
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article/show";
    }

    // article 수정 폼
    @GetMapping("/{id}/updateForm")
    public String updateForm(@PathVariable Long id, @SessionAttribute("sessionedUser") User user, Model model) {
        Article article = articleService.getArticle(id, user);
        model.addAttribute("article", article);
        return "article/updateForm";
    }

    // article 수정
    @PutMapping("/{id}")
    public String updateArticle(@PathVariable Long id, @SessionAttribute("sessionedUser") User user, ArticleForm articleForm) {
        Article updateArticle = Article.of(articleForm);
        updateArticle.setId(id);
        updateArticle = articleService.updateArticle(updateArticle, user);
        return "redirect:/articles/{id}";
    }

    // article 삭제
    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable Long id, @SessionAttribute("sessionedUser") User user) {
        articleService.deleteArticle(id, user);
        return "redirect:/articles";
    }
}
