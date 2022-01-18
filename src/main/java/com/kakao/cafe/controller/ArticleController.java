package com.kakao.cafe.controller;

import com.kakao.cafe.dto.AuthDto;
import com.kakao.cafe.dto.PageRequestDto;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/articles")
@Slf4j
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/new")
    public String writeArticleForm(HttpSession session) {
        log.debug("[Get] /articles/new");
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        return "article/write";
    }

    @GetMapping("/{articleId}")
    public String readArticle(@PathVariable Long articleId, Model model) {
        log.debug("[Get] /articles/" + articleId);
        model.addAttribute("article", articleService.read(articleId));
        return "article/read";
    }

    @PostMapping("/new")
    public String writeArticle(ArticleDto articleDto, HttpSession session) {
        log.debug("[Post] /articles " + articleDto);
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        articleService.register(articleDto);
        return "redirect:/";
    }

    @GetMapping
    public String articleFList(PageRequestDto pageRequestDto, Model model) {
        log.debug("[Get] /articles " + pageRequestDto);
        model.addAttribute("articles", articleService.getList(pageRequestDto));
        return "article/list";
    }
}
