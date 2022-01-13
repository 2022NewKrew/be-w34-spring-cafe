package com.kakao.cafe.main;

import com.kakao.cafe.article.Article;
import com.kakao.cafe.article.ArticleDto;
import com.kakao.cafe.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final ArticleService articleService;

    @Autowired
    public MainController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(path = {"/", "/index"})
    public String createUserForm(Model model) {
        List<ArticleDto> articles = articleService.getAllArticles().stream().map(ArticleDto::new).collect(Collectors.toList());
        model.addAttribute("articles", articles);
        return "index";
    }

}
