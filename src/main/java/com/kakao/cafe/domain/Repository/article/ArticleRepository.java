package com.kakao.cafe.domain.Repository.article;

import com.kakao.cafe.domain.Entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
    private static final List<Article> articles = new ArrayList<>();

    public void postNewArticle(Article article) {
        articles.add(article);
    }

    public List<Article> findAllArticles() {
        return articles;
    }

    public Article findArticleById(int articleId) {
        return articles.get(articleId - 1);
    }
}
