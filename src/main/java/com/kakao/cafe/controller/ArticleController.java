package com.kakao.cafe.controller;

import com.kakao.cafe.controller.interceptor.ValidateLogin;
import com.kakao.cafe.dto.ArticleRequestDTO;
import com.kakao.cafe.dto.ArticleResponseDTO;
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
        logger.info("index test");
        List<ArticleResponseDTO> articles = articleService.readAll();
        if(articles.size() > 0) {
            logger.info("getArticleList: {}, {}", articles.get(0).getId(), articles.get(0).getAuthor());
        }
        model.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        ArticleResponseDTO article = articleService.read(id);
        model.addAttribute("article", article);
        return "article/show";
    }

    @ValidateLogin
    @PostMapping("/articles")
    public String createArticle(ArticleRequestDTO articleRequestDto) {
        articleService.create(articleRequestDto);
        return "redirect:/";
    }
}
