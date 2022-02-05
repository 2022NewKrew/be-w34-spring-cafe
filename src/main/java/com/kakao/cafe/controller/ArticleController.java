package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Articles;
import com.kakao.cafe.domain.Id;
import com.kakao.cafe.domain.UserId;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticleFormDto;
import com.kakao.cafe.dto.ArticlePreviewDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.anotation.Login;
import com.kakao.cafe.util.mapper.ArticleMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String showArticles(Model module) {
        Articles articles = articleService.findAll();
        List<ArticlePreviewDto> articlesDto = ArticleMapper.toArticlePreviewDto(articles);
        module.addAttribute("articles", articlesDto);
        return "index";
    }

    @GetMapping("/question/{articleId}")
    public String showArticle(@PathVariable Long articleId, Model module) {
        Article article = articleService.findByArticleId(new Id(articleId));
        ArticleDto articleDto = ArticleMapper.toArticleDto(article);
        module.addAttribute("article", articleDto);
        return "qna/show";
    }

    @PostMapping("/question/create")
    public String createArticle(@ModelAttribute ArticleFormDto articleFormDto, @Login UserId loginId) {
        Article article = ArticleMapper.toArticle(articleFormDto);
        articleService.post(article, loginId);
        return "redirect:/";
    }

    @GetMapping("/question/{articleId}/form")
    public String modifyArticleForm(@PathVariable Long articleId, @Login UserId loginId, Model module) {
        Article article = articleService.findByArticleIdAndLoginId(new Id(articleId), loginId);
        ArticleDto articleDto = ArticleMapper.toArticleDto(article);
        module.addAttribute("article", articleDto);
        return "qna/updateForm";
    }

    @PutMapping("/question/{articleId}/update")
    public String modifyArticle(@PathVariable Long articleId, @ModelAttribute ArticleFormDto articleFormDto, @Login UserId loginId) {
        Article article = ArticleMapper.toArticle(articleId, articleFormDto);
        articleService.modify(article, loginId);
        return "redirect:/question/{articleId}";
    }

    @DeleteMapping("/question/{articleId}/delete")
    public String modifyArticle(@PathVariable Long articleId, @Login UserId loginId) {
        articleService.delete(new Id(articleId), loginId);
        return "redirect:/";
    }
}