package com.kakao.cafe.web;

import com.kakao.cafe.domain.article.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
    private final List<Article> articleList = new ArrayList<>();

    @GetMapping("/")
    public String printArticles(Model model) {
        model.addAttribute("articles", articleList);
        return "/index";
    }

    @GetMapping("/articles/{index}")
    public String getArticle(@PathVariable int index, Model model) {
        Article target = articleList.get(index - 1);
        model.addAttribute("article", target);
        return "/qna/show";
    }

    @PostMapping("/questions")
    public String addArticle(String writer, String title, String contents) {
        articleList.add(new Article(articleList.size() + 1, writer, title, contents));
        return "redirect:/";
    }
}
