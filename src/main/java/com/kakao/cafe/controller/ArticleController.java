package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.dto.article.ArticleContentDto;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ArticleWriteDto;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import com.kakao.cafe.utils.DtoConversion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    public String requestArticleList(Model model) {
        List<ArticleDto> articleList = articleService.getArticleList();
        model.addAttribute("articles", articleList);
        return "articles/list";
    }

    @PostMapping("")
    public String requestArticleRegister(@Valid ArticleWriteDto articleWriteDto) {
        articleService.registerArticle(articleWriteDto);
        return "redirect:/articles";
    }

    @GetMapping("/{articleId}")
    public String requestArticleDetail(@PathVariable UUID articleId, Model model) {
        ArticleContentDto articleContent = articleService.findArticleById(articleId);
        model.addAttribute("article", articleContent);
        return "articles/detail";
    }
}
