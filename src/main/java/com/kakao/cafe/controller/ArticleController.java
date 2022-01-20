package com.kakao.cafe.controller;

import java.time.format.DateTimeFormatter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.article.service.dto.ArticleReadServiceResponse;
import com.kakao.cafe.article.service.dto.CreateArticleServiceRequest;
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
        articleService.createArticle(createArticle(req, authInfo));
        return "redirect:/";
    }

    @PutMapping("")
    @AuthInfoCheck
    public String updateArticle(@ModelAttribute ArticleUpdateRequest req,
                                @SessionAttribute(Constant.authAttributeName) AuthInfo authInfo) {
        log.info("PUT /article {}", req.getArticleId());
        articleService.validateAuthor(Long.parseLong(req.getArticleId()), authInfo.getId());
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

    @DeleteMapping("/{id}")
    @AuthInfoCheck
    public String deleteArticle(@PathVariable String id,
                                @SessionAttribute(Constant.authAttributeName) AuthInfo authInfo) {
        log.info("DELETE /article {}", id);
        articleService.validateAuthor(Long.parseLong(id), authInfo.getId());
        articleService.deleteArticle(Long.parseLong(id));
        return "redirect:/";
    }


    @GetMapping("/update/{articleId}")
    @AuthInfoCheck
    public String updateArticleFrom(@PathVariable String articleId, Model model,
                                    @SessionAttribute(Constant.authAttributeName) AuthInfo authInfo) {
        log.info("GET /article/update/{}", articleId);
        articleService.validateAuthor(Long.parseLong(articleId), authInfo.getId());
        ArticleReadServiceResponse dto = articleService.getArticleReadViewDTO(Long.parseLong(articleId));
        model.addAttribute("stringId", authInfo.getStringId());
        model.addAttribute("title", dto.getTitle());
        model.addAttribute("contents", dto.getContents());
        model.addAttribute("articleId", articleId);
        return "article/update";
    }

    private CreateArticleServiceRequest createArticle(ArticleCreateRequest req, AuthInfo authInfo) {
        return CreateArticleServiceRequest.builder()
                                          .authorId(authInfo.getId())
                                          .authorStringId(authInfo.getStringId())
                                          .title(req.getTitle())
                                          .contents(req.getContents())
                                          .build();
    }
}
