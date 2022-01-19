package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleDto;
import com.kakao.cafe.article.dto.ArticleFormDto;
import com.kakao.cafe.article.dto.ArticleMapper;
import com.kakao.cafe.article.service.ArticlePostService;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.config.Login;
import com.kakao.cafe.user.domain.UserId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final ArticlePostService articlePostService;
    private final ArticleMapper articleMapper;

    public ArticleController(ArticleService articleService, ArticlePostService articlePostService, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.articlePostService = articlePostService;
        this.articleMapper = articleMapper;
    }

    @GetMapping("/")
    public String showArticles(Model module) {
        List<Article> articles = articleService.findArticles();
        module.addAttribute("articles", articleMapper.toListArticleDto(articles));
        return "index";
    }

    @PostMapping("/question/create")
    public String createArticle(@ModelAttribute ArticleFormDto articleFormDto) {
        Article article = articleMapper.toArticle(articleFormDto);
        articlePostService.postArticle(article);
        return "redirect:/";
    }

    @GetMapping("/question/{articleId}")
    public String showArticle(@PathVariable Long articleId, Model module) {
        Article article = articleService.findByArticleId(articleId);
        ArticleDto articleDto = articleMapper.toArticleDto(article);
        module.addAttribute("articles", articleDto);
        return "qna/show";
    }

    @GetMapping("/question/{articleId}/form")
    public String modifyArticleForm(@PathVariable Long articleId, @Login UserId loginId, Model module) {
        Article article = articlePostService.findByArticleIdAndLoginId(articleId, loginId);
        ArticleDto articleDto = articleMapper.toArticleDto(article);
        module.addAttribute("article", articleDto);
        return "qna/updateForm";
    }

    @PutMapping("/question/{articleId}/update")
    public String modifyArticle(@PathVariable Long articleId, @ModelAttribute ArticleFormDto articleFormDto, @Login UserId loginId) {
        Article article = articleMapper.toArticle(articleId, articleFormDto);
        articlePostService.modify(article, loginId);
        return "redirect:/question/{articleId}";
    }

    @DeleteMapping("/question/{articleId}/delete")
    public String modifyArticle(@PathVariable Long articleId, @Login UserId loginId) {
        articlePostService.delete(articleId, loginId);
        return "redirect:/";
    }
}