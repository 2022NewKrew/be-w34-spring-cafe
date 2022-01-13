package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.dto.article.ArticleCreateRequestDto;
import com.kakao.cafe.web.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ArticleController {

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public String createArticle(ArticleCreateRequestDto articleCreateRequestDto) {
        logger.info("POST /articles: request {}", articleCreateRequestDto);

        // article 생성
        Article article = new Article();
        article.setTitle(articleCreateRequestDto.getTitle());
        article.setContent(articleCreateRequestDto.getContent());
        articleService.write(article);
        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String showArticle(Model model, @PathVariable int index) {
        logger.info("GET /articles/{}: response article detail page", index);

        // article 조회
        Optional<Article> article = articleService.findArticle(index);
        if (article.isEmpty()) {
            return "error/404";
        }
        model.addAttribute("article", article.get());
        return "article/show";
    }
}
