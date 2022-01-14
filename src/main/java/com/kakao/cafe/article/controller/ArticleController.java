package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.ArticleRequestDTO;
import com.kakao.cafe.article.dto.ArticleResponseDTO;
import com.kakao.cafe.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @GetMapping("/")
    public String getArticles(Model model) {
        List<ArticleResponseDTO> articles = articleService.findAll();

        model.addAttribute("totalSize", articles.size());
        model.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("/articleForm")
    public String getArticleForm() {
        return "articles/form";
    }

    @PostMapping("/articles")
    public String postArticles(@Valid ArticleRequestDTO articleRequestDTO) {
        logger.info(articleRequestDTO.toString());
        articleService.posting(articleRequestDTO);
        return "redirect:/";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findOne(id));
        return "articles/show";
    }
}
