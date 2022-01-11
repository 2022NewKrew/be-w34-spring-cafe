package com.kakao.cafe.domain.repository;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Draft;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class ArticleRepository {

    private final List<Article> data = new ArrayList<>();

    public Article create(Draft draft) {
        long id = data.size() + 1;
        Date createdAt = new Date();
        Article article = draft.createArticle(id, createdAt);
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
