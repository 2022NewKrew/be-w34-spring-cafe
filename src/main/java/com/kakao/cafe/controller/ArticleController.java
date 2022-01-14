package com.kakao.cafe.controller;

import com.kakao.cafe.dto.RequestArticleDto;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @PostMapping("/posts")
    public String addArticle(@ModelAttribute RequestArticleDto articleDto) {
        logger.info("POST /posts {}", articleDto);
        articleService.addPost(articleDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getAllArticles(Model model) {
        model.addAttribute("posts", articleService.findArticles());
        return "index";
    }

    @GetMapping("/articles/{id}")
    public String getArticleDetail(@PathVariable int id, Model model) {
        model.addAttribute("post", articleService.findOne(id));
        return "post/show";
    }


}
