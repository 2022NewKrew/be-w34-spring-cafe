package com.kakao.cafe.controller;

import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.controller.aop.AuthInfoCheck;
import com.kakao.cafe.controller.session.AuthInfo;
import com.kakao.cafe.controller.session.HttpSessionUtil;
import com.kakao.cafe.controller.viewdto.request.ArticleCreateRequest;
import com.kakao.cafe.controller.viewdto.response.ArticleReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    private final ArticleService articleService;
    private final Long fakeSession = 1L; // 글쓰기는 기본 사용자만 가능(로그인 기능이 없기 때문에 유지)

    @PostMapping("")
    @AuthInfoCheck
    public String postArticle(@ModelAttribute ArticleCreateRequest req) {
        log.info("POST /article {}", req.getTitle());
        // 유저 정보에 맞춰서 서비스 호출
        articleService.createArticle(fakeSession, req.getTitle(), req.getContents());
        return "redirect:/";
    }

    @GetMapping("/form")
    @AuthInfoCheck
    public String getNewArticleForm(Model model, HttpSession session) {
        log.info("Get /article/form");
        // 유저 정보를 모델로 보내기
        AuthInfo authInfo = HttpSessionUtil.getAuthInfo(session);
        return "article/form";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable("id") String articleId, Model model) {
        log.info("Get /article/{}", articleId);
        model.addAllAttributes(new ArticleReadResponse(articleService.getArticleReadViewDTO(Long.parseLong(articleId))));
        return "article/show";
    }
}
