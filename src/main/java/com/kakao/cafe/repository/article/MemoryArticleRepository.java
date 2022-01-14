package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
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
    public Optional<Article> findById(Long id) {
        return store.stream()
                .filter(article -> article.getId() == id)
                .findAny();
    }

    @Override
    public List<Article> findAll() {
        return store;
    }
}
