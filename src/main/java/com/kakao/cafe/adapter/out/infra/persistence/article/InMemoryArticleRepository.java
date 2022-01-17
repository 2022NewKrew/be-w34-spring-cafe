package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.domain.article.Article;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryArticleRepository implements ArticleRepository {

    private static final int FIRST_ID = 1;

    private final Map<Integer, Article> repository;
    private final AtomicInteger atomicInt = new AtomicInteger(FIRST_ID);

    public InMemoryArticleRepository() {
        repository = new HashMap<>();
    }

    public void save(Article article) {
        article.setId(atomicInt.getAndIncrement());
        repository.put(article.getId(), article);
    }

    public List<ArticleVO> getAllArticleList() {
        return repository.values().stream().map(ArticleVO::from).collect(Collectors.toList());
    }

    public Optional<ArticleVO> findById(int index) {
        return Optional.of(ArticleVO.from(repository.get(index)));
    }
}
