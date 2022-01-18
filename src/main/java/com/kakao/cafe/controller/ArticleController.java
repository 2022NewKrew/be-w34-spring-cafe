package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.LoginRequired;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.article.ArticleDetailResponseDto;
import com.kakao.cafe.dto.article.ArticleListResponseDto;
import com.kakao.cafe.dto.article.ArticleRegisterRequestDto;
import com.kakao.cafe.mapper.ArticleMapper;
import com.kakao.cafe.service.ArticleService;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    public ArticleController(ArticleService articleService, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.articleMapper = articleMapper;
    }

    @GetMapping("/articles")
    public String requestArticleList(Model model) {
        List<Article> articleList = articleService.getArticleList();
        List<ArticleListResponseDto> dtoList = articleMapper.articleListToArticleListResponseDtoList(articleList);
        model.addAttribute("articles", dtoList);
        return "articles/list";
    }

    @LoginRequired
    @GetMapping("/article/form")
    public String requestArticleRegisterForm() {
        return "articles/form";
    }

    @LoginRequired
    @PostMapping("/articles")
    public String requestArticleRegister(@Valid ArticleRegisterRequestDto dto, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        Article article = articleMapper.articleRegisterRequestDtoToArticle(dto, user);
        articleService.registerArticle(article);
        return "redirect:/articles";
    }

    @LoginRequired
    @GetMapping("/articles/{articleId}")
    public String requestArticleDetail(@PathVariable UUID articleId, Model model) {
        Article article = articleService.findArticleById(articleId);
        ArticleDetailResponseDto dto = articleMapper.articleToArticleDetailResponseDto(article);
        model.addAttribute("article", dto);
        return "articles/detail";
    }
}
