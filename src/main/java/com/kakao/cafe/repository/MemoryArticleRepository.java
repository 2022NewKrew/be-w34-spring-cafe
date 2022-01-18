package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryArticleRepository implements ArticleRepository{

    private List<Article> articles;

    MemoryArticleRepository() {
        articles = new ArrayList<>();
        Article article = new Article("오늘 뭐먹지??", "뭐먹을지 너무 고민된다.", "wldus");
        article.generateId(1);
        articles.add(article);
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }

    @Override
    public Optional<Article> findById(int id) {
        return Optional.of(articles.get(id-1));
    }

    @Override
    public int save(Article article) {
        article.generateId(articles.size()+1);
        articles.add(article);
        return article.getId();
    }

    @Override
    public int update(Article article) {
        Article updateArticle = articles.get(article.getId());
        updateArticle.setTitle(article.getTitle());
        updateArticle.setContent(article.getContent());
        return updateArticle.getId();
    }

    @Override
    public int delete(Article article) {
        return 0;
    }
}
