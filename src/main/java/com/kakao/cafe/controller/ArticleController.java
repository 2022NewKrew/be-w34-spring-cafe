package com.kakao.cafe.controller;

import com.kakao.cafe.dto.article.ArticleCreationDTO;
import com.kakao.cafe.dto.article.ArticleDTO;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.Url;
import com.kakao.cafe.util.View;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public String getAllArticle(Model model) {
        var articleList = articleService.findAllArticles();
        model.addAttribute("articleList", articleList);
        model.addAttribute("articleListSize", articleList.size());
        return View.INDEX;
    }

    @PostMapping("/articles")
    public String createArticle(ArticleCreationDTO dto) {
        long id = articleService.post(dto);
        return "redirect:/articles/" + id;
    }

    @GetMapping("/articles/{id}")
    public String getArticlePage(@PathVariable long id, Model model) {
        try {
            model.addAttribute("article", getArticle(id));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "redirect:/" + Url.ARTICLES;
        }

        return View.ARTICLES_SHOW;
    }

    private ArticleDTO getArticle(long id) throws IllegalArgumentException {
        return articleService.findById(id);
    }

}
