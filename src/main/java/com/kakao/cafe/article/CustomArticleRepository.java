package com.kakao.cafe.article;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Repository
public class CustomArticleRepository implements ArticleRepository {

    private final Map<String, Article> articleMap;

    private final Map<String, Article> articleMapByTitle;

    public CustomArticleRepository() {
        this.articleMap = new HashMap<>();
        this.articleMapByTitle = new HashMap<>();
    }

    @Override
    public void save(Article article) {
        if (articleMap.containsKey(article.getWriter())) throw new IllegalArgumentException();
        article.setTime(LocalDateTime.now());
        articleMap.put(article.getWriter(), article);
        articleMapByTitle.put(article.getTitle(), article);
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        if (!articleMapByTitle.containsKey(title)) throw new IllegalArgumentException();
        return Optional.of(articleMapByTitle.get(title));
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(articleMap.values());
    }

}
