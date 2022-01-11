package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

//@Repository
public class ArticleMemoryRepository implements ArticleRepository {

    private AtomicLong id = new AtomicLong(0L);
    private Map<Long, Article> postList = new HashMap<>();

    @Override
    public Long save(Article article) {
        long articleId = id.incrementAndGet();
        postList.put(articleId, article);
        return articleId;
    }

    @Override
    public List<Article> findAll() {
        return postList.values().stream().collect(Collectors.toList());
    }

    @Override
    public Article findById(Long postId) {
        return postList.get(postId);
    }
}
