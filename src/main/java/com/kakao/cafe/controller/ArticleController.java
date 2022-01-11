package com.kakao.cafe.controller;

import com.kakao.cafe.dao.article.ArticleDao;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ArticleController {
    private static final int MAX_ARTICLES = 1;
    private static final int INDEX_OF_FIRST_ARTICLE = 0;

    private final ArticleDao articleDao;

    @Autowired
    public ArticleController(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("articles", articleDao.getPartOfArticles(INDEX_OF_FIRST_ARTICLE, MAX_ARTICLES));
        model.addAttribute("pages", getPages());
        return "index";
    }

    @GetMapping("/index/{page}")
    public String getIndexByPage(@PathVariable int page, Model model) {
        model.addAttribute("articles", articleDao.getPartOfArticles((page - 1) * MAX_ARTICLES, page * MAX_ARTICLES));
        model.addAttribute("pages", getPages());
        return "index";
    }

    @PostMapping("/articles")
    public String postArticle(ArticleDto articleDto) {
        articleDao.add(new Article(articleDao.getSize() + 1, articleDto.getTitle(), articleDto.getWriter(), articleDto.getContents()));
        return "redirect:/";
    }

    @GetMapping("/articles/{id}")
    public String getArticleDetail(@PathVariable int id, Model model) {
        model.addAttribute("article", articleDao.findArticleById(id));
        return "qna/show";
    }

    private List<Integer> getPages() {
        return IntStream
                .rangeClosed(1, Math.floorDiv(articleDao.getSize() - 1, MAX_ARTICLES) + 1)
                .boxed()
                .collect(Collectors.toList());
    }
}
