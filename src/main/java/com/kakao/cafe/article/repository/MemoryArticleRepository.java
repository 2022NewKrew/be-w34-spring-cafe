package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryArticleRepository implements ArticleRepository {

    private static long sequenceNumber = 0;
    private static final List<Article> memoryRepository = new ArrayList<>();

    @Override
    public Article save(Article article) {
        sequenceNumber++;
        article.setId(sequenceNumber);
        memoryRepository.add(article);
        return article;
    }

    @Override
    public Optional<Article> findOne(Long id) {
        return Optional.ofNullable(memoryRepository.get(id.intValue() - 1));
    }

    @Override
    public List<Article> findAll() {
        return memoryRepository;
    }

    @Override
    public void update(Long id) {

    }
}
