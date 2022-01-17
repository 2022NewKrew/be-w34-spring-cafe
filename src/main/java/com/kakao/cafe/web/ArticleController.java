package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        try {
            model.addAttribute("article", articleRepository.getArticleList());
        } catch(Exception e) {
            logger.info("GET / 실패: {}", e.getMessage());
        }
        return "index";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable int id, Model model) {
        try {
            Article article = articleRepository.getArticle(id);
            model.addAttribute("article", article);
        } catch(Exception e) {
            logger.info("GET /article/id 실패: {}", e.getMessage());
            return "redirect:/";
        }
        return "qna/show";
    }

    @PostMapping("/questions")
    public String postQuestions(Article article) {
        try {
            articleRepository.addArticle(article);
        } catch(Exception e) {
            logger.info("POST /questions 실패: {}", e.getMessage());
        }
        return "redirect:/";
    }
}
