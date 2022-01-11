package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryArticleRepository implements ArticleRepository {

    private final List<Article> articleDataBase = new ArrayList<>();
    private long sequence = 0;

    @Override
    public void save(Article article) {
        article.setId(++sequence);
        articleDataBase.add(article);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articleDataBase.stream()
                .filter(article -> article.getId() == id)
                .findAny();
    }

    @Override
    public List<Article> findAll() {
        return List.copyOf(articleDataBase);
    }

}
