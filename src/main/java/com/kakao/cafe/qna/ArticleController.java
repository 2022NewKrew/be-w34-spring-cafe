package com.kakao.cafe.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 1:48
 */
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/questions")
    public String question(ArticleDto articleDto) {
        Article article = new Article(
                articleDto.getWriter(),
                articleDto.getTitle(),
                articleDto.getContents(),
                0,
                LocalDateTime.now(),
                LocalDateTime.now());
        articleService.saveArticle(article);

        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String articleView(Model model, @PathVariable("index") int index) {
        Article article = articleService.findArticleById(index);
        model.addAttribute("article", new ArticleDto(article));
        return "/qna/show";
    }
}
