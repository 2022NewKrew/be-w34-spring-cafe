package com.kakao.cafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final List<Article> articles = new ArrayList<>();

    @PostMapping("/questions")
    public String postQuestions(String writer, String title, String contents) {
        logger.info("[postQuestions] writer = {}, title = {}, contents = {}", writer, title, contents);
        articles.add(new Article(writer, title, contents));
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
}
