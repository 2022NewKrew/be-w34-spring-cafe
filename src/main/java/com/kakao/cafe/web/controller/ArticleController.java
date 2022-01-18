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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ArticleController {

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article/createForm")
    public String createForm(HttpSession session) {
        logger.info("GET /article/form: response article create page");
        // sessionUser 확인
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/user/loginForm";
        }
        return "article/createForm";
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

    @GetMapping("/articles/{id}")
    public String showArticle(Model model, @PathVariable Long id, HttpSession session) {
        logger.info("GET /articles/{}: response article detail page", id);

        // sessionUser 확인
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/user/loginForm";
        }

        // article 조회
        Optional<Article> article = articleService.findArticle(id);
        if (article.isEmpty()) {
            return "error/404";
        }

        boolean isWriter = Objects.equals(article.get().getWriter(), sessionUser.getUserId());

        model.addAttribute("article", article.get());
        model.addAttribute("isWriter", isWriter);
        return "article/show";
    }

    @GetMapping("/articles/{id}/form")
    public String updateForm(Model model, @PathVariable Long id, HttpSession session) {
        // article 찾기
        Optional<Article> article = articleService.findArticle(id);
        if (article.isEmpty()) {
            return "error/404";
        }
        // sessionUser 찾기
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || !article.get().getWriter().equals(sessionUser.getUserId())) {
            return "error/401";
        }
        // writer 와 sessionUser 가 일치하면 수정 페이지 응답
        logger.info("GET /articles/{}/form: response article edit page with {}", id, article.get());
        model.addAttribute("article", article.get());
        return "article/updateForm";
    }

    @PutMapping("/articles/{id}/update")
    public String updateArticle(Article newArticle, @PathVariable Long id, HttpSession session) {
        // article 찾기
        Optional<Article> article = articleService.findArticle(id);
        if (article.isEmpty()) {
            return "error/404";
        }
        // sessionUser 찾기
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || !article.get().getWriter().equals(sessionUser.getUserId())) {
            return "error/401";
        }
        // article 수정
        logger.info("PUT /articles/{}/update: request {} and update", id, newArticle);
        try {
            articleService.update(newArticle, id);
            return "redirect:/articles/{id}";
        } catch (IllegalArgumentException e) {
            return "error/404";
        }
    }

    @DeleteMapping ("/articles/{id}/delete")
    public String deleteArticle(@PathVariable Long id, HttpSession session) {
        // article 찾기
        Optional<Article> article = articleService.findArticle(id);
        if (article.isEmpty()) {
            return "error/404";
        }
        // sessionUser 찾기
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || !article.get().getWriter().equals(sessionUser.getUserId())) {
            return "error/401";
        }
        // article 삭제
        logger.info("DELETE /articles/{}/delete: request article delete", id);
        try {
            articleService.delete(id);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            return "error/404";
        }
    }
}
