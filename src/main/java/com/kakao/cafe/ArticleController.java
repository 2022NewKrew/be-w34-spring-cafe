package com.kakao.cafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final List<Article> articles = new ArrayList<>();

    @PostMapping("/questions")
    public String postQuestions(String writer, String title, String contents) {
        logger.info("[postQuestions] writer = {}, title = {}, contents = {}", writer, title, contents);
        articles.add(new Article(articles.size() + 1, writer, title, contents));
        return "redirect:/";
    }

    @GetMapping("/")
    public String getMain(Model model) {
        logger.info("[getMain]");
        List<Map<String, String>> articleList = new ArrayList<>();
        for(int i = 0; i < articles.size(); i++) {
            articleList.add(Map.of("index", Integer.toString(i+1),
                    "title", articles.get(i).getTitle(),
                    "writer", articles.get(i).getWriter()));
        }
        model.addAttribute("articles", articleList);
        return "qna/list";
    }

    @GetMapping("/articles/{index}")
    public String getArticle(@PathVariable String index, Model model) {
        int id = Integer.parseInt(index);
        Article selectedArticle = articles.get(id - 1);
        model.addAttribute("writer", selectedArticle.getWriter());
        model.addAttribute("title", selectedArticle.getTitle());
        model.addAttribute("contents", selectedArticle.getContents());
        return "qna/show";
    }
}
