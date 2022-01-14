package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;

import java.util.*;

public class MemoryArticleRepository implements ArticleRepository {

    private final Map<Long, Article> articleMap = new HashMap<>();
    private Long idNumber = 0L;

    @Override
    public Long generateId() {
        return ++idNumber;
    }

    @Override
    public void create(Article article) {
        articleMap.put(article.getId(), article);
    }

    @Override
    public List<Article> findAll() {
        return List.copyOf(articleMap.values());
    }

    @Override
    public Article findById(Long id) {
        Optional<Article> result =  Optional.ofNullable(articleMap.get(id));
        if (result.isPresent()) {
            return result.get();
        }
        throw new RuntimeException("일치하는 게시글이 존재하지 않습니다.");
    }
}
