package com.kakao.cafe.service;

import com.kakao.cafe.vo.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{
    private final List<Article> articles = new ArrayList<>();

    @Override
    public void write(Article article) {
        article.setArticleId(articles.size() + 1);
        articles.add(article);
    }

    @Override
    public List<Article> getArticles() {
        return articles;
    }

    @Override
    public Article findByArticleId(String articleId) {
        return null;
    }
}
