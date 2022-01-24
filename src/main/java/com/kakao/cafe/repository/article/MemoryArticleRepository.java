package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryArticleRepository implements ArticleRepository {

    private static List<Article> store;
    private static long sequence;

    public MemoryArticleRepository() {
        store = new ArrayList<>();
        sequence = 0L;
    }

    @Override
    public Article save(Article article) {
        article.setId(sequence++);
        store.add(article);
        return article;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public Article increaseViewCount(Article article) {
        return null;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return store.stream()
                .filter(article -> article.getId() == id)
                .findAny();
    }

    @Override
    public Optional<Long> findUidById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<String> findUserNicknameById(Long userId) {
        return Optional.empty();
    }

    @Override
    public List<Article> findAll() {
        return store;
    }
}
