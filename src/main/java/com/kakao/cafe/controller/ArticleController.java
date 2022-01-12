package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.dto.article.ArticleDetailResponseDto;
import com.kakao.cafe.dto.article.ArticleListResponseDto;
import com.kakao.cafe.dto.article.ArticleRegisterRequestDto;
import com.kakao.cafe.mapper.ArticleMapper;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;
    private final ArticleMapper articleMapper;

    public ArticleController(ArticleService articleService, UserService userService, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.userService = userService;
        this.articleMapper = articleMapper;
    }

    @GetMapping("/")
    public String requestArticleList(Model model) {
        List<Article> articleList = articleService.getArticleList();
        List<ArticleListResponseDto> dtoList = articleList.stream()
                .map(articleMapper::articleToArticleListResponseDto)
                .collect(Collectors.toList());
        model.addAttribute("articles", dtoList);
        return "articles/list";
    }

    @PostMapping("/articles")
    public String requestArticleRegister(@Valid ArticleRegisterRequestDto dto) {
        UserName userName = new UserName(dto.getUserName());
        User user = userService.findUserByUserName(userName);
        Article article = articleMapper.articleRegisterRequestDtoToArticle(dto, user);
        articleService.registerArticle(article);
        return "redirect:/";
    }

    @GetMapping("/articles/{articleId}")
    public String requestArticleDetail(@PathVariable UUID articleId, Model model) {
        Article article = articleService.findArticleById(articleId);
        ArticleDetailResponseDto dto = articleMapper.articleToArticleDetailResponseDto(article);
        model.addAttribute("article", dto);
        return "articles/detail";
    }
}
