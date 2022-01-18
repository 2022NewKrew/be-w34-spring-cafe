package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.article.ArticleCreateRequestDto;
import com.kakao.cafe.web.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ArticleController {

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article/form")
    public String createForm(HttpSession session) {
        logger.info("GET /article/form: response article create page");
        // sessionUser 확인
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/user/loginForm";
        }
        return "article/form";
    }

    @PostMapping("/articles")
    public String createArticle(ArticleCreateRequestDto articleCreateRequestDto, HttpSession session) {
        logger.info("POST /articles: request {}", articleCreateRequestDto);

        // sessionUser 확인
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/user/loginForm";
        }

        // article 생성
        Article article = new Article();
        article.setTitle(articleCreateRequestDto.getTitle());
        article.setContent(articleCreateRequestDto.getContent());
        article.setWriter(sessionUser.getUserId());
        articleService.write(article);
        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String showArticle(Model model, @PathVariable Long index, HttpSession session) {
        logger.info("GET /articles/{}: response article detail page", index);

        // sessionUser 확인
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/user/loginForm";
        }

        // article 조회
        Optional<Article> article = articleService.findArticle(index);
        if (article.isEmpty()) {
            return "error/404";
        }
        model.addAttribute("article", article.get());
        return "article/show";
    }
}
