package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleCreateRequest;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/articles", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createArticle(ArticleCreateRequest request) {
        log.info("start createArticle()");
        articleService.createArticle(request);
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("articles", articleService.list());
        return "/index";
    }

    @GetMapping("/articles/{articleId}")
    public String detail(@PathVariable int articleId, Model model) {
        model.addAttribute("article", articleService.detail(articleId));
        return "/articles/show";
    }

}
