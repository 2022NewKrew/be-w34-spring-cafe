package com.kakao.cafe.article;

import org.springframework.stereotype.Repository;

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
    public void update(Article article) {
        if (articleMap.containsKey(article.getWriter())) throw new IllegalArgumentException();
        articleMap.put(article.getWriter(), article);
        articleMapByTitle.put(article.getTitle(), article);
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        if (articleMapByTitle.containsKey(title) == false) throw new IllegalArgumentException();
        return Optional.of(articleMapByTitle.get(title));
    }

    @Override
    public List<Article> getAllArticles() {
        return new ArrayList<>(articleMap.values());
    }

}
