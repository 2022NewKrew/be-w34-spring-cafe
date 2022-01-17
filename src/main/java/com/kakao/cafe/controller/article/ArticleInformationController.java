package com.kakao.cafe.controller.article;

import com.kakao.cafe.controller.article.dto.ArticleDto;
import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.service.ArticleService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleInformationController {

    private static final int MAX_ARTICLES = 1;
    private static final int INDEX_OF_FIRST_ARTICLE = 1;

    private final ArticleService articleService;

    public ArticleInformationController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        List<Article> articles = articleService.getPartOfArticles(INDEX_OF_FIRST_ARTICLE,
                MAX_ARTICLES);
        List<ArticleDto> articleDtos = articleToArticleDto(articles);
        model.addAttribute("articles", articleDtos);
        model.addAttribute("pages", articleService.getPages(MAX_ARTICLES));
        return "index";
    }

    @GetMapping("/index/{page}")
    public String getIndexByPage(@PathVariable int page, Model model) {
        List<Article> articles = articleService.getPartOfArticles(page, MAX_ARTICLES);
        List<ArticleDto> articleDtos = articleToArticleDto(articles);
        model.addAttribute("articles", articleDtos);
        model.addAttribute("pages", articleService.getPages(MAX_ARTICLES));
        return "index";
    }

    @GetMapping("/articles/{id}")
    public String getArticleDetail(@PathVariable int id, Model model) {
        Article article = articleService.findArticleById(id);
        ArticleDto articleDto = new ArticleDto(article);
        model.addAttribute("article", articleDto);
        return "qna/show";
    }

    private List<ArticleDto> articleToArticleDto(List<Article> articles) {
        return articles
                .stream()
                .map(ArticleDto::new)
                .collect(Collectors.toList());
    }
}
