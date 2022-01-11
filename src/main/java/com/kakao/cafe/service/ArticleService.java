package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleCreateRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private static final List<Article> articles = new ArrayList<>();

    public void createArticle(ArticleCreateRequest request) {
        articles.add(new Article(Article.cnt++, request.getWriter(), request.getTitle(), request.getContents()));
    }

    public List<Article> list() {
        return articles;
    }

    public Article detail(int articleId) {
        return articles.get(articleId - 1);
    }
}
