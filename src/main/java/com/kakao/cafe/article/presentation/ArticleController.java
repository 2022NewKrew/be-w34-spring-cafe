package com.kakao.cafe.article.presentation;

import com.kakao.cafe.article.application.ArticleService;
import com.kakao.cafe.article.dto.ArticleListResponse;
import com.kakao.cafe.article.dto.ArticleSaveRequest;
import com.kakao.cafe.article.dto.ArticleShowResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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

    @PostMapping
    public String save(ArticleSaveRequest request) {
        log.info(this.getClass() + ": 게시글 작성");
        articleService.save(request);
        return "redirect:/articles";
    }

    @GetMapping
    public ModelAndView findAll(Map<String, Object> model) {
        log.info(this.getClass() + ": 게시글 목록");
        List<ArticleListResponse> articleListResponses = articleService.findAll();
        model.put("articles", articleListResponses);
        return new ModelAndView("index", model);
    }

    @GetMapping("{articleId}")
    public ModelAndView findById(@PathVariable String articleId, Map<String, Object> model) {
        log.info(this.getClass() + ": 게시글 상세보기");
        ArticleShowResponse articleShowResponse = articleService.findById(articleId);
        model.put("article", articleShowResponse);
        return new ModelAndView("qna/show", model);
    }
}
