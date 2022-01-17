package com.kakao.cafe.controller.article;

import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ArticleController {

    private static final int MAX_ARTICLES = 1;
    private static final int INDEX_OF_FIRST_ARTICLE = 1;

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        List<ArticleDto> articleDtos = articleToArticleDto(
                articleService.getPartOfArticles(INDEX_OF_FIRST_ARTICLE, MAX_ARTICLES));
        model.addAttribute("articles", articleDtos);
        model.addAttribute("pages", articleService.getPages(MAX_ARTICLES));
        return "index";
    }

    @GetMapping("/index/{page}")
    public String getIndexByPage(@PathVariable int page, Model model) {
        List<ArticleDto> articleDtos = articleToArticleDto(
                articleService.getPartOfArticles(page, MAX_ARTICLES));
        model.addAttribute("articles", articleDtos);
        model.addAttribute("pages", articleService.getPages(MAX_ARTICLES));
        return "index";
    }

    @PostMapping("/articles")
    public String postArticle(ArticleCreateDto articleCreateDto) {
        articleService.createArticle(articleCreateDto.getTitle(), articleCreateDto.getWriter(),
                articleCreateDto.getContents());
        return "redirect:/";
    }

    @GetMapping("/articles/{id}")
    public String getArticleDetail(@PathVariable int id, Model model) {
        model.addAttribute("article", articleService.findArticleById(id));
        return "qna/show";
    }

    private List<ArticleDto> articleToArticleDto(List<Article> articles) {
        return articles
                .stream()
                .map(ArticleDto::new)
                .collect(Collectors.toList());
    }
}
