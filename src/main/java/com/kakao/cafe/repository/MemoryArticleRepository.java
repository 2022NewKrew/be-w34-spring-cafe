package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MemoryArticleRepository implements ArticleRepository{

    private List<Article> articles;

    MemoryArticleRepository() {
        articles = new ArrayList<>();
        Article article = new Article("오늘 뭐먹지??", "뭐먹을지 너무 고민된다.", "wldus", new Date(System.currentTimeMillis()));
        article.generateId(1);
        articles.add(article);
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }

    @Override
    public Article findById(int id) {
        return articles.get(id-1);
    }

    @Override
    public int save(Article article) {
        article.generateId(articles.size()+1);
        articles.add(article);
        return article.getId();
    }
}
