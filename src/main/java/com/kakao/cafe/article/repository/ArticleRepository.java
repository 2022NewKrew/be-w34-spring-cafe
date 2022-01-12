package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.entity.Article;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepository {

    private final List<Article> articleList;
    private Integer identity;

    private ArticleRepository() {
        this.articleList = new ArrayList<>();
        this.identity = 0;
    }

    public int save(Article article) {
        article.setId(++this.identity);
        LocalDateTime createdAt = LocalDateTime.now();
        article.setCreatedAt(createdAt);

        this.articleList.add(article);

        return this.identity;
    }

    public List<Article> findAll() {
        return Collections.unmodifiableList(this.articleList);
    }

    public Optional<Article> findById(Integer id) {
        return articleList.stream()
                          .filter(article -> article.getId().equals(id))
                          .findFirst();
    }
}
