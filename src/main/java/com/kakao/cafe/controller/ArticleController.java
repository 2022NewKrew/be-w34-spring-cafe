package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ArticleController {
    private static final int MAX_ARTICLES = 5;
    private static final int INDEX_OF_FIRST_ARTICLE = 0;

    private final List<Article> articles = new LinkedList<>();

    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("articles", getArticles(INDEX_OF_FIRST_ARTICLE, MAX_ARTICLES));
        model.addAttribute("pages", getPages());
        return "index";
    }

    @GetMapping("/index/{page}")
    public String getIndexByPage(@PathVariable int page, Model model) {
        model.addAttribute("articles", getArticles((page - 1) * MAX_ARTICLES, page * MAX_ARTICLES));
        model.addAttribute("pages", getPages());
        return "index";
    }

    @PostMapping("/articles")
    public String postArticle(ArticleDto articleDto) {
        articles.add(0, new Article(articles.size() + 1, articleDto.getTitle(), articleDto.getWritter(), articleDto.getContents()));
        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String getArticleDetail(@PathVariable int index, Model model) {
        model.addAttribute("article", findArticleByIndex(index));
        return "qna/show";
    }

    private Article findArticleByIndex(int index) {
        return articles
                .stream()
                .filter(article -> article.getId() == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시글이 없습니다."));
    }

    private List<Article> getArticles(int startIndex, int finishIndex) {
        if (finishIndex > articles.size()) {
            return new ArrayList<>(articles.subList(startIndex, articles.size()));
        }
        return new ArrayList<>(articles.subList(startIndex, finishIndex));
    }

    private List<Integer> getPages() {
        if (articles.size() <= MAX_ARTICLES) {
            return handleFirstPage();
        }

        return IntStream
                .rangeClosed(0, Math.floorDiv(articles.size() - 1, MAX_ARTICLES))
                .map(page -> page + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    private List<Integer> handleFirstPage() {
        return new ArrayList<>();
    }
}
