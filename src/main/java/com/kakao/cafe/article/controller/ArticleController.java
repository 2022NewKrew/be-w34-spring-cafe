package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.request.ArticleCreateRequest;
import com.kakao.cafe.article.dto.response.ArticleDetailResponse;
import com.kakao.cafe.article.exception.ArticleNotFoundException;
import com.kakao.cafe.article.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    protected ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 메인 페이지 접속 [GET]
     */
    @GetMapping("/")
    public String getIndexPage(Model model){
        this.log.info("[GET] / - 메인 페이지 접속(글 리스트)");
        List<ArticleDetailResponse> articleList = this.articleService.getArticleList();
        model.addAttribute("articles", articleList);

        return "../static/index";
    }

    /**
     * 게시글 작성 페이지 접속 [GET]
     */
    @GetMapping("/articles/write")
    public String getArticleCreatePage() {
        this.log.info("[GET] /article/write - 게시글 작성 페이지 접속");
        return "article/write";
    }

    /**
     * 게시글 상세 페이지 접속 [GET]
     * @param id: 보고자 하는 게시글의 ID(PK)
     * @throws ArticleNotFoundException: 해당 ID 의 Article 이 존재하지 않을 경우 발생
     */
    @GetMapping("/articles/{id}")
    public String getArticleDetailPage(Model model, @PathVariable("id") Long id) {
        this.log.info("[GET] /article/{} - (id: {}) 게시글 상세 페이지 접속", id, id);

        ArticleDetailResponse articleDetail = this.articleService.getArticleDetail(id);
        model.addAttribute("article", articleDetail);

        return "article/show";
    }

    /**
     * 게시글 작성 요청 [POST]
     * @param req: 게시글 작성 정보
     */
    @PostMapping("/articles")
    public String createArticle(ArticleCreateRequest req) {
        this.log.info("[POST] /article - 게시글 작성 요청");

        this.articleService.createArticle(req);

        return "redirect:/";
    }
}
