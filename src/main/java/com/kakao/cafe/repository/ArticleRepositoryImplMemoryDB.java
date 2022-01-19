package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepositoryImplMemoryDB implements ArticleRepository{

    private final List<Article> articleMemoryDB = new CopyOnWriteArrayList<>();

    @Override
    public void createArticle(Article article) {
        articleMemoryDB.add(article);
    }

    @Override
    public Integer articlesSize() {
        return articleMemoryDB.size();
    }

    @Override
    public List<Article> readArticleList() {
        return articleMemoryDB;
    }

    @Override
    public boolean isIdUsed(Integer aid) {
        return articlesSize() >= aid;
    }

    @Override
    public Article readArticle(Integer aid) {
        return articleMemoryDB.get(aid - 1);
    }
}
