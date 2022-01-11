package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.QnaWriteRequestDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    private ArticleService articleService;

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String list(Model model) {
        List<Article> articleList = articleService.getArticleAll();
        logger.info(articleList.toString());
        model.addAttribute("articles", articleList);
        return "qna/list";
    }

    @GetMapping("/articles/{articleId}")
    public String details(@PathVariable Long articleId, Model model) {
        Article article = articleService.getArticle(articleId);
        logger.info(article.toString());
        model.addAttribute("article", article);
        return "qna/show";
    }

    @PostMapping("/questions")
    public String questions(QnaWriteRequestDto qnaWriteRequestDto) {
        logger.info(qnaWriteRequestDto.toString());
        articleService.writeArticle(qnaWriteRequestDto.getWriter(), qnaWriteRequestDto.getTitle(), qnaWriteRequestDto.getContents());
        return "redirect:/";
    }
}
