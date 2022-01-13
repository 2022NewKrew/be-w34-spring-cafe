package com.kakao.cafe.repository;

import com.kakao.cafe.vo.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository("articleRepository")
public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();

    public void createArticle(Article article) {
        article.setArticleId(articles.size() + 1);
        articles.add(article);
    }

    public List<Article> readArticles() {
        return articles;
    }

    public Article readArticle(Integer articleId) {
        return articles.stream()
                .filter(article -> Objects.equals(article.getArticleId(), articleId))
                .findFirst()
                .orElse(null);
    }
}
