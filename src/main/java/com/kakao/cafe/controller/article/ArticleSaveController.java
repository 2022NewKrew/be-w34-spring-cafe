package com.kakao.cafe.controller.article;

import com.kakao.cafe.controller.article.dto.ArticleCreateDto;
import com.kakao.cafe.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleSaveController {

    private final ArticleService articleService;

    public ArticleSaveController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public String postArticle(ArticleCreateDto articleCreateDto) {
        articleService.createArticle(
                articleCreateDto.getTitle(),
                articleCreateDto.getWriter(),
                articleCreateDto.getContents()
        );
        return "redirect:/";
    }
}
