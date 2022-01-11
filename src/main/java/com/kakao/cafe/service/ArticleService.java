package com.kakao.cafe.service;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.ArticleRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private final List<Article> articleList = new ArrayList<>();

    public void createQuestion(ArticleRequest articleRequest) {
        Article article = new Article(articleRequest, articleList.size()+1);
        articleList.add(article);
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public Article findById(String index) {
        return articleList.get(Integer.parseInt(index)-1);
    }
}
