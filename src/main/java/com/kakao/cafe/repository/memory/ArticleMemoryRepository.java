package com.kakao.cafe.repository.memory;

import com.kakao.cafe.controller.dto.ArticleSaveForm;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.ArticleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

//@Repository
public class ArticleMemoryRepository implements ArticleRepository {

    private AtomicLong id = new AtomicLong(0L);
    private Map<Long, ArticleSaveForm> postList = new HashMap<>();

    @Override
    public Long save(ArticleSaveForm article) {
        long articleId = id.incrementAndGet();
        postList.put(articleId, article);
        return articleId;
    }

    @Override
    public List<Article> findAll() {
        return postList.values().stream().map(Article::from).collect(Collectors.toList());
    }

    @Override
    public Article findById(Long postId) {
        return Article.from(postList.get(postId));
    }
}
