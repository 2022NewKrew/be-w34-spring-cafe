package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.request.WriteArticleRequest;
import com.kakao.cafe.article.dto.response.ArticleDetailResponse;
import com.kakao.cafe.article.dto.response.ArticleResponse;
import com.kakao.cafe.article.dto.response.ArticlesResponse;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.user.dto.response.UsersResponse;
import com.kakao.cafe.user.service.UserService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;
    private final UserService userService;

    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping("/articles/writing")
    public String searchUsers(Model model) {
        UsersResponse usersResponse = userService.findAll();
        logger.info("글을 쓸 회원 목록 조회 완료");
        model.addAttribute("writeUsers", usersResponse);
        return "/post/write";
    }

    @PostMapping("/articles")
    public String writeArticle(@Valid WriteArticleRequest writeArticleRequest) {
        ArticleResponse articleResponse = articleService.save(writeArticleRequest);
        logger.info("글쓰기 완료: {}", articleResponse);
        return "redirect:/";
    }

    @GetMapping("/")
    public String searchArticles(Model model) {
        ArticlesResponse articlesResponse = articleService.findAll();
        logger.info("글목록 조회 완료");
        model.addAttribute("articles", articlesResponse);
        return "/index";
    }

    @GetMapping("/articles/{id}")
    public String searchArticle(Model model, @PathVariable Long id) {
        ArticleDetailResponse articleDetailResponse = articleService.findById(id);
        logger.info("글조회 완료: {}", articleDetailResponse);
        model.addAttribute("article", articleDetailResponse);
        return "/post/show";
    }
}
