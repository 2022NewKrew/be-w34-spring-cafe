package com.kakao.cafe.controller;

import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.controller.viewdto.response.ArticleListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class RootController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String getLandingPage(Model model) {
        log.info("GET / access, getLandingPage");
        model.addAllAttributes(new ArticleListResponse(articleService.getAllArticleViewDTO(0L)));
        return "index";
    }

    @GetMapping("*")
    public String wildCard(Model model) {
        log.info("RootController.wildCard redirect");
        return "redirect:";
    }
}
