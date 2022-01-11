package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryArticleRepository implements ArticleRepository {

    private static long sequenceNumber = 0;
    private static final List<Article> memoryRepository = new ArrayList<>();

    @Override
    public Long save(Article article) {
        sequenceNumber++;
        article.setId(sequenceNumber);
        memoryRepository.add(article);
        return sequenceNumber;
    }

    @Override
    public Article findOne(Long id) {
        return memoryRepository.get(id.intValue() - 1);
    }

    @Override
    public List<Article> findAll() {
        return memoryRepository;
    }

    @Override
    public void update(Long id) {

    }
}
