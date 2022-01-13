package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.domain.article.Article;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class InMemoryArticleInfoRepository implements ArticleInfoRepository {

    private final Map<Integer, Article> repository;

    public InMemoryArticleInfoRepository() {
        repository = new HashMap<>();
    }

    public void save(Article articleInfoEntity) {
        repository.put(articleInfoEntity.getId(), articleInfoEntity);
    }

    public List<Article> getAllArticleList() {
        return new ArrayList<>(repository.values());
    }

    public Optional<Article> findByIndex(int index) {
        return Optional.ofNullable(repository.get(index));
    }
}
