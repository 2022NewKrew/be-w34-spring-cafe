package com.kakao.cafe.controller;

import java.time.format.DateTimeFormatter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.article.service.dto.ArticleReadServiceResponse;
import com.kakao.cafe.config.Constant;
import com.kakao.cafe.controller.interceptor.AuthInfoCheck;
import com.kakao.cafe.controller.session.AuthInfo;
import com.kakao.cafe.controller.viewdto.request.ArticleCreateRequest;
import com.kakao.cafe.controller.viewdto.request.ArticleUpdateRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("")
    @AuthInfoCheck
    public String postArticle(@ModelAttribute ArticleCreateRequest req,
                              @SessionAttribute(Constant.authAttributeName) AuthInfo authInfo) {
        log.info("POST /article {}", req.getTitle());
        articleService.createArticle(authInfo.getId(), req.getTitle(), req.getContents());
        return "redirect:/";
    }

    @PutMapping("")
    @AuthInfoCheck
    public String updateArticle(@ModelAttribute ArticleUpdateRequest req,
                                @SessionAttribute(Constant.authAttributeName) AuthInfo authInfo) {
        log.info("PUT /article {}", req.getArticleId());
        articleService.isAuthorOfArticle(Long.parseLong(req.getArticleId()), authInfo.getId());
        articleService.updateArticle(Long.parseLong(req.getArticleId()), req.getTitle(), req.getContents());
        return "redirect:/";
    }

    @GetMapping("/form")
    @AuthInfoCheck
    public String getNewArticleForm(Model model, @SessionAttribute(Constant.authAttributeName) AuthInfo authInfo) {
        log.info("Get /article/form");
        model.addAttribute("stringId", authInfo.getStringId());
        model.addAttribute("title", "");
        model.addAttribute("contents", "");
        return "article/form";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable("id") String articleId, Model model) {
        log.info("Get /article/{}", articleId);
        ArticleReadServiceResponse dto = articleService.getArticleReadViewDTO(Long.parseLong(articleId));
        model.addAttribute("title", dto.getTitle());
        model.addAttribute("authorstringid", dto.getAuthorStringId());
        model.addAttribute("writedate", dto.getMakeTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        model.addAttribute("contents", dto.getContents());
        model.addAttribute("articleId", dto.getId());
        return "article/show";
    }

    @GetMapping("/update/{articleId}")
    @AuthInfoCheck
    public String updateArticleFrom(@PathVariable String articleId, Model model,
                                    @SessionAttribute(Constant.authAttributeName) AuthInfo authInfo) {
        log.info("GET /article/update/{}", articleId);
        ArticleReadServiceResponse dto = articleService.getArticleReadViewDTO(Long.parseLong(articleId));

        if (!authInfo.getId().equals(dto.getAuthorId())) {
            throw new IllegalArgumentException("자신의 글만 수정 가능합니다.");
        }
        model.addAttribute("stringId", authInfo.getStringId());
        model.addAttribute("title", dto.getTitle());
        model.addAttribute("contents", dto.getContents());
        model.addAttribute("articleId", articleId);
        return "article/update";
    }

}
