package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.domain.Comment;
import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.article.ArticleCreateRequestDto;
import com.kakao.cafe.web.exception.UnauthorizedException;
import com.kakao.cafe.web.service.ArticleService;
import com.kakao.cafe.web.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
public class ArticleController {

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;
    private final CommentService commentService;

    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping("/article/createForm")
    public String createForm() {
        logger.info("GET /article/form: response article create page");
        return "article/createForm";
    }

    @PostMapping("/articles")
    public String createArticle(ArticleCreateRequestDto articleCreateRequestDto) {
        logger.info("POST /articles: request {}", articleCreateRequestDto);

        // article 생성
        Article article = new Article();
        article.setTitle(articleCreateRequestDto.getTitle());
        article.setContent(articleCreateRequestDto.getContent());
        article.setWriter(articleCreateRequestDto.getWriter());
        articleService.write(article);
        return "redirect:/";
    }

    @GetMapping("/articles/{id}")
    public String showArticle(Model model, @PathVariable Long id, HttpSession session) {
        logger.info("GET /articles/{}: response article detail page", id);

        // article 조회
        Article article = articleService.findArticle(id);

        User sessionUser = (User) session.getAttribute("sessionUser");
        boolean isWriter = Objects.equals(article.getWriter(), sessionUser.getUserId());

        // comments 조회
        List<Comment> comments = commentService.findComments(id);

        model.addAttribute("article", article);
        model.addAttribute("isWriter", isWriter);
        model.addAttribute("comments", comments);
        return "article/show";
    }

    @GetMapping("/articles/{id}/form")
    public String updateForm(Model model, @PathVariable Long id, HttpSession session) {
        // article 찾기
        Article article = articleService.findArticle(id);

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (!article.getWriter().equals(sessionUser.getUserId())) {
            throw new UnauthorizedException("글을 수정할 권한이 없습니다.");
        }

        // writer 와 sessionUser 가 일치하면 수정 페이지 응답
        logger.info("GET /articles/{}/form: response article edit page with {}", id, article);
        model.addAttribute("article", article);
        return "article/updateForm";
    }

    @PutMapping("/articles/{id}/update")
    public String updateArticle(Article newArticle, @PathVariable Long id, HttpSession session) {
        // article 찾기
        Article article = articleService.findArticle(id);

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (!article.getWriter().equals(sessionUser.getUserId())) {
            throw new UnauthorizedException("글을 수정할 권한이 없습니다.");
        }

        // article 수정
        logger.info("PUT /articles/{}/update: request {} and update", id, newArticle);
        articleService.update(newArticle, id);
        return "redirect:/articles/{id}";
    }

    @DeleteMapping("/articles/{id}/delete")
    public String deleteArticle(@PathVariable Long id, HttpSession session) {
        // article 찾기
        Article article = articleService.findArticle(id);

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (!article.getWriter().equals(sessionUser.getUserId())) {
            throw new UnauthorizedException("글을 삭제할 권한이 없습니다.");
        }

        // article 삭제
        logger.info("DELETE /articles/{}/delete: request article delete", id);
        articleService.delete(id);
        return "redirect:/";
    }
}
