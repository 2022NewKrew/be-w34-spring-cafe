package com.kakao.cafe.repository;

import com.kakao.cafe.db.JakeDB;
import com.kakao.cafe.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDao implements ArticleDAOInterface {
    private final JakeDB jakeDB;

    @Autowired
    public ArticleDao(JakeDB jakeDB) {
        this.jakeDB = jakeDB;
    }

    @Override
    public void save(Article article) {
        jakeDB.getArticleList().add(article);
    }

    @Override
    public List<Article> findAll() {
        return jakeDB.getArticleList();
    }

    @Override
    public Article findById(long id) {
        return jakeDB.getArticleList()
                .stream()
                .filter(article -> article.getId() == (id))
                .findFirst()
                .orElse(null);
    }
}
