package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class ArticleRepository {

    private final List<Article> data = new ArrayList<>();

    public Article create(Article article) {
        long id = data.size() + 1;
        article.setId(id);
        Date createdAt = new Date();
        article.setCreatedAt(createdAt);
        data.add(article);
        return article;
    }
}
