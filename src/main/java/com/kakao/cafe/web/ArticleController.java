package com.kakao.cafe.web;

import com.kakao.cafe.domain.dto.ArticleCreateCommand;
import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    private final ArticleRepository articleRepository = new ArticleRepository();

    @GetMapping("/")
    public String listArticles(Model model) {
        model.addAttribute("articles", articleRepository.toList());
        return "/index";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable int id, Model model) {
        Article target = articleRepository.retrieve(id - 1);
        model.addAttribute("article", target);
        return "/qna/show";
    }

    @PostMapping("/questions")
    public String addArticle(String writer, String title, String contents) {
        articleRepository.store(new ArticleCreateCommand(writer, title, contents));
        return "redirect:/";
    }
}
