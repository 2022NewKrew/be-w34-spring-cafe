package com.kakao.cafe.web;

import com.kakao.cafe.error.ErrorResponse;
import com.kakao.cafe.exception.UnauthorizedException;
import com.kakao.cafe.exception.UserUnmatchedException;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.web.dto.article.ArticleCreateRequestDto;
import com.kakao.cafe.web.dto.article.ArticleResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    private static final String ARTICLE_WRITE = "게시물 쓰기";
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("article/form")
    public String articleWritePage(HttpSession session, Model model) {
        this.articleService.preventNotLoggedInUser(session);
        model.addAttribute("title", ARTICLE_WRITE);
        return "/article/form";
    }


    @PostMapping("article/create")
    public String articleWrite(ArticleCreateRequestDto articleCreateRequestDto, HttpSession session) {
        logger.info("article: {}", articleCreateRequestDto);
        this.articleService.postArticle(articleCreateRequestDto, session);
        return "redirect:/";
    }

    @GetMapping("/")
    public String articleMain(Model model) {
        List<ArticleResponseDto> articleList = this.articleService.readAll();
        model.addAttribute("articles", articleList);
        model.addAttribute("size", articleList.size());
        return "index";
    }

    @GetMapping("article/{articleIndex}")
    public String articleDetail(@PathVariable String articleIndex, HttpSession httpSession, Model model) {
        this.articleService.articleDetail(articleIndex, httpSession, model);
        return "/article/show";
    }

    @GetMapping("article/{articleIndex}/editPage")
    public String articleEditPage(@PathVariable String articleIndex, HttpSession httpSession, Model model) {
        this.articleService.articleEditPage(articleIndex, httpSession, model);
        return "/article/form";
    }

    @PutMapping("/article/{articleIndex}/edit")
    public String editArticle(@PathVariable String articleIndex, ArticleCreateRequestDto articleCreateRequestDto) {
        this.articleService.editArticle(articleIndex, articleCreateRequestDto);
        return "redirect:/";
    }

    @DeleteMapping("/article/{articleIndex}/delete")
    public String deleteArticle(@PathVariable String articleIndex, HttpSession session) {
        this.articleService.deleteArticle(articleIndex, session);
        return "redirect:/";
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<ErrorResponse> handleUserNotLoggedInException(UnauthorizedException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler({UserUnmatchedException.class})
    public ResponseEntity<ErrorResponse> handleUserUnmatchedException(UserUnmatchedException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }
}
