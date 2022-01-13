package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleRequestDto;
import com.kakao.cafe.dto.ArticleResponseDto;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @GetMapping()
    public String getArticleList(Model model) {
        List<ArticleResponseDto> articles = articleService.readAll();
        if(articles.size() > 0) {
            logger.info("getArticleList: {}, {}", articles.get(0).getId(), articles.get(0).getAuthor());
        }
        model.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        ArticleResponseDto article = articleService.read(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        model.addAttribute("article", article);
        return "article/show";
    }

    @PostMapping("/articles")
    public String createArticle(ArticleRequestDto articleRequestDto) {
        articleService.create(articleRequestDto);
        return "redirect:/";
    }
}
