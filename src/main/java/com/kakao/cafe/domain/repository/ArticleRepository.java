package com.kakao.cafe.domain.repository;

import com.kakao.cafe.domain.entity.Article;
import org.springframework.lang.Nullable;
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

    public List<Article> list() {
        return Collections.unmodifiableList(data);
    }

    @Nullable
    public Article getById(long id) {
        return data.stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
