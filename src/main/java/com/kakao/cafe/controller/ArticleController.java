package com.kakao.cafe.controller;

import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.controller.viewdto.request.ArticleCreateRequest;
import com.kakao.cafe.controller.viewdto.response.ArticleReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    private final ArticleService articleService;
    private final Long fakeSession = 0L;



    @PostMapping("")
    public String postArticle(@ModelAttribute ArticleCreateRequest req) {
        log.info("POST /article {}", req.getTitle());
        articleService.createArticle(fakeSession, req.getTitle(), req.getContents());

        return "redirect:/";
    }

    @GetMapping("/form")
    public String getNewArticleForm(Model model) {
        log.info("Get /article/form");
        return "article/form";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable("id") String articleId, Model model) {
        log.info("Get /article/{}", articleId);
        model.addAllAttributes(new ArticleReadResponse(articleService.getArticleReadViewDTO(Long.parseLong(articleId))));
        return "article/show";
    }


}
