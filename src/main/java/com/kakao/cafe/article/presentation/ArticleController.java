package com.kakao.cafe.article.presentation;

import com.kakao.cafe.article.application.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.kakao.cafe.article.presentation.ArticleController.ARTICLE_URI;

@Controller
@Slf4j
@RequestMapping(ARTICLE_URI)
public class ArticleController {

    private final ArticleService articleService;

    public static final String ARTICLE_URI = "/articles";

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
}
