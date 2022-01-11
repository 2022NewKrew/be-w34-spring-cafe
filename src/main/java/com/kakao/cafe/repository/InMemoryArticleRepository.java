package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleId;
import com.kakao.cafe.domain.article.Articles;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryArticleRepository implements ArticleRepository {

    private final Articles articles;

    public InMemoryArticleRepository() {
        this.articles = new Articles();
    }

    @Override
    public synchronized void save(Article article) {
        articles.add(article);
    }

    @Override
    public List<Article> findAll() {
        return articles.getArticleList();
    }

    @Override
    public Optional<Article> findArticleById(ArticleId id) {
        return articles.findByArticleId(id);
    }
}
