package com.kakao.cafe.service;

import com.kakao.cafe.vo.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Article findByArticleId(Integer articleId) {
        return articles.stream()
                .filter(article -> Objects.equals(article.getArticleId(), articleId))
                .findFirst()
                .orElse(null);
    }
}
