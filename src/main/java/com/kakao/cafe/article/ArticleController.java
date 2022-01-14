package com.kakao.cafe.article;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleDto;
import com.kakao.cafe.article.dto.ArticleFormDto;
import com.kakao.cafe.article.dto.ArticleListDto;
import com.kakao.cafe.article.dto.ArticleMapper;
import com.kakao.cafe.article.service.ArticlePostService;
import com.kakao.cafe.article.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final ArticlePostService articlePostService;

    public ArticleController(ArticleService articleService, ArticlePostService articlePostService) {
        this.articleService = articleService;
        this.articlePostService = articlePostService;
    }

    @GetMapping("/question/form")
    public String showArticleForm() {
        return "qna/form";
    }

    @PostMapping("/question/create")
    public String createArticle(ArticleFormDto articleFormDto) {
        Article article = ArticleMapper.toArticle(articleFormDto);
        articlePostService.postArticle(article);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showArticles(Model module) {
        List<ArticleListDto> articles = ArticleMapper.toListArticleDto(articleService.findArticles());
        module.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("/articles/{index}")
    public String showArticle(@PathVariable Long index, Model module) {
        ArticleDto articles = ArticleMapper.toArticleDto(articleService.findArticleByArticleId(index));
        module.addAttribute("articles", articles);
        return "qna/show";
    }
}