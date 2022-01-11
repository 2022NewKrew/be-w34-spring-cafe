package com.kakao.cafe.repository;

import com.kakao.cafe.entiry.Article;

import java.util.*;

//@Repository
public class MemoryArticleRepository implements ArticleRepository{

    private static Map<Long, Article> store = new HashMap<>();
    private static Long sequence = 0L;


    @Override
    public Article save(Article article) {
        article.setId(++sequence);
        store.put(sequence, article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Article> findByWriter(String writer) {
        return store.values().stream()
                .filter(article -> article.getWriterUserId().equals(writer))
                .findAny();
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(store.values());
    }
}
