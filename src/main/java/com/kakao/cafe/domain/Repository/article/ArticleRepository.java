package com.kakao.cafe.domain.Repository.article;

import com.kakao.cafe.domain.Entity.Article;
import com.kakao.cafe.exceptions.NoSuchArticleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
    private static final Map<Integer, Article> articles = new HashMap<>();
    private AtomicInteger sequential = new AtomicInteger(0);

    public void postNewArticle(Article article) {
        articles.put(article.getArticleId(), article);
    }

    public List<Article> findAllArticles() {
        return new ArrayList<>(articles.values());
    }

    public Article findArticleById(int articleId) throws NoSuchArticleException {
        if (articles.containsKey(articleId)) {
            return articles.get(articleId);
        }
        throw new NoSuchArticleException();
    }

    public int getNextArticleId() {
        return sequential.incrementAndGet();
    }
}
