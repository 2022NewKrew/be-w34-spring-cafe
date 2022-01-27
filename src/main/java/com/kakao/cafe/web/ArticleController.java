package com.kakao.cafe.web;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.error.ErrorResponse;
import com.kakao.cafe.exception.UnauthorizedException;
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
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("article/create")
    public String articleWrite(ArticleCreateRequestDto articleCreateRequestDto, HttpSession session) {
        logger.info("article: {}", articleCreateRequestDto);
        this.articleService.postArticle(Article.fromPost(articleCreateRequestDto.getTitle(), articleCreateRequestDto.getContent()), session);
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
        this.articleService.ArticleDetail(articleIndex, httpSession, model);
        return "/article/show";
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<ErrorResponse> handleUserNotLoggedInException(UnauthorizedException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

}
