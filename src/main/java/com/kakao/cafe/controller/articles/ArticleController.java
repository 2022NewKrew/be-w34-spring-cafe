package com.kakao.cafe.controller.articles;

import com.kakao.cafe.controller.articles.dto.ArticleDetailDto;
import com.kakao.cafe.controller.articles.dto.ArticleItemDto;
import com.kakao.cafe.controller.articles.dto.ArticleWriteRequestDto;
import com.kakao.cafe.service.article.ArticleService;
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
        List<ArticleItemDto> articles = articleService.getArticleAll();
        model.addAttribute("articles", articles);
        return "qna/list";
    }

    @GetMapping("/articles/{articleId}")
    public String details(@PathVariable Long articleId, Model model) {
        ArticleDetailDto articleDetail = articleService.getArticleDetail(articleId);
        model.addAttribute("article", articleDetail);
        return "qna/show";
    }

    @PostMapping("/articles")
    public String questions(ArticleWriteRequestDto articleWriteRequest) {
        articleService.writeArticle(articleWriteRequest.getWriter(), articleWriteRequest.getTitle(), articleWriteRequest.getContents());
        return "redirect:/";
    }
}
