package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.request.ArticleCreateRequest;
import com.kakao.cafe.article.dto.response.ArticleDetailResponse;
import com.kakao.cafe.article.exception.ArticleNotFoundException;
import com.kakao.cafe.article.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final Logger logger;

    private ArticleController(ArticleService articleService) {
        this.articleService = articleService;
        this.logger = LoggerFactory.getLogger(ArticleController.class);
    }

    @GetMapping("/")
    public String getIndexPage(Model model){
        this.logger.info("[GET] / - 메인 페이지 접속(글 리스트)");
        List<ArticleDetailResponse> articleList = articleService.getArticleList();
        model.addAttribute("articles", articleList);

        return "../static/index";
    }

    @GetMapping("/articles/write")
    public String getArticleCreatePage() {
        this.logger.info("[GET] /article/write - 게시글 작성 페이지 접속");
        return "article/write";
    }

    @GetMapping("/articles/{id}")
    public String getArticleDetailPage(Model model, @PathVariable("id") Integer id) {
        this.logger.info("[GET] /article/{} - (id: {}) 게시글 상세 페이지 접속", id, id);
        try {
            ArticleDetailResponse articleDetail = articleService.getArticleDetail(id);
            model.addAttribute("article", articleDetail);

            return "article/show";
        } catch(ArticleNotFoundException e) {
            logger.error("[ERROR] - {}", e.getMessage());

            return "redirect:/";
        }
    }

    @PostMapping("/articles")
    public String createArticle(ArticleCreateRequest req) {
        this.logger.info("[POST] /article - 게시글 작성 요청");

        this.articleService.createArticle(req);

        return "redirect:/";
    }
}
