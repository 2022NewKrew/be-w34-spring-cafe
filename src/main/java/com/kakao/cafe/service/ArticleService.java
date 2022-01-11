package com.kakao.cafe.service;

import com.kakao.cafe.model.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private final List<Article> articleList = new ArrayList<>();

    public void createQuestion(Article article) {
        articleList.add(article);
    }

    public List<Article> getArticleList() {
        return articleList;
    }
}
